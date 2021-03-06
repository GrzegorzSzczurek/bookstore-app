package com.wojcik.demo.controller;

import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.BookService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class GreetingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/")
    public ModelAndView show(HttpSession session) {

        session.removeAttribute("user-session");

        return new ModelAndView("index", "user", new User());
    }

    @RequestMapping("/home")
    public ModelAndView process(@ModelAttribute("user") User user, HttpSession session /* ModelMap model */ ) {

        user = userService.get(user.getUsername(), user.getPassword());

        if(session.getAttribute("user-session") == null)
            session.setAttribute("user-session", user);
        else
            user = (User) session.getAttribute("user-session");

        if(user == null) return new ModelAndView("index", "user", new User());

        if(user.isAdmin()) {

//            List<Book> books = bookService.getBooks();
//
//            ModelAndView modelAndView = new ModelAndView("admin");
//
//            modelAndView.addObject("books", books);
//            modelAndView.addObject("user", user);

            return new ModelAndView("redirect:/admin/");
        }

        return new ModelAndView("redirect:/user/");
    }

    @RequestMapping("/registerForm")
    public ModelAndView showRegisterForm() {

        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping("/registerForm/process")
    public String processRegisterForm(@Valid @ModelAttribute("user") User user,
                                      BindingResult result, Model model) {

        model.addAttribute("result", result);

        if (result.hasErrors()
                || userService.isUsernameTaken(user.getUsername())
                || userService.isEmailTaken(user.getEmail())) {
            return "register";
        }

        userService.save(user);

        return "register-done";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("purchase-session");

        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
