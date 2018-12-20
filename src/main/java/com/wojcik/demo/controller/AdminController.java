package com.wojcik.demo.controller;

import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.BookService;
import com.wojcik.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView show(@ModelAttribute("user") User user, HttpSession session) {

        user = (User) session.getAttribute("user-session");

        if(user == null) return new ModelAndView("redirect:/home", "user", new User());

        List<Book> books = bookService.getBooks();

        ModelAndView modelAndView = new ModelAndView("admin");

        modelAndView.addObject("books", books);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/add-book")
    public ModelAndView showBookForm() {

        return new ModelAndView("add-book", "book", new Book());
    }

    @RequestMapping("/add-book/process")
    public String processBook(@Valid @ModelAttribute("book") Book book,
                                    BindingResult result, Model model) {

        model.addAttribute("result", result);

        if(result.hasErrors()) return "add-book";

        bookService.save(book);

        return "redirect:/admin/";

    }

    @GetMapping("/remove-book")
    public String remove(@RequestParam("bookId") int bookId) {

        bookService.remove(bookId);

        return "redirect:/admin/";
    }

    @GetMapping("/update-book")
    public ModelAndView update(@RequestParam("bookId") int bookId, Model model) {

        Book book = bookService.getById(bookId);

        return new ModelAndView("update-book", "book", book);
    }

    @RequestMapping("/update-book/process")
    public String processUpdateBook(@Valid @ModelAttribute("book") Book book,
                              BindingResult result, Model model) {

        model.addAttribute("result", result);

        if(result.hasErrors()) return "update-book";

        Book updatedBook = bookService.getById(book.getId());

        updatedBook = book;

        bookService.save(updatedBook);

        return "redirect:/admin/";
    }

    @RequestMapping("/showUsers")
    public String showUsers(Model model) {

        List<User> users = userService.getUsers();

        model.addAttribute("users", users);

        return "show-users";
    }

    @GetMapping("/remove-user")
    public String removeUser(@RequestParam("userId") int userId) {

        userService.remove(userId);

        return "redirect:/admin/showUsers";
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
