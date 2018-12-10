package com.wojcik.demo.controller;

import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView show() {

        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping("/process")
    public String process(@ModelAttribute("user") User user, Model model) {

        user = userService.get(user.getUsername(), user.getPassword());

        model.addAttribute("loggedUser", user);

        if(user == null) return "index";

        return "logged-in";
    }

    @RequestMapping("/registerForm")
    public ModelAndView showRegisterForm() {

        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping("/registerForm/process")
    public String processRegisterForm(@Valid @ModelAttribute("user") User user,
                                      BindingResult result, Model model) {

        model.addAttribute("result", result);

        if (result.hasErrors()) {
            return "register";
        }

        userService.save(user);

        return "register-done";
    }

    @RequestMapping("/showUsers")
    public String showUsers(Model model) {

        List<User> users = userService.getUsers();

        model.addAttribute("users", users);

        return "show-users";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
