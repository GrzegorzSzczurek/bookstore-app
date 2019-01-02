package com.wojcik.demo.controller;

import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.PurchaseDetails;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.BookService;
import com.wojcik.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping("/")
    public ModelAndView show(@ModelAttribute("user") User user, HttpSession session) {

        user = (User) session.getAttribute("user-session");

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        if(purchase == null) {

            purchase = new Purchase();

            session.setAttribute("purchase-session", purchase);
        }

        List<Book> books = bookService.getBooks();

        ModelAndView modelAndView = new ModelAndView("logged-in");

        modelAndView.addObject("user", user);
        modelAndView.addObject("books", books);
        modelAndView.addObject("purchase", purchase);

        return modelAndView;
    }

    @RequestMapping("/add-to-cart")
    public ModelAndView addToCart(@RequestParam("bookId") int bookId, HttpSession session) {

        Book book = bookService.getById(bookId);

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        PurchaseDetails purchaseDetails =
                new PurchaseDetails(purchase, book);

        purchase.addDetails(purchaseDetails);

        session.setAttribute("purchase-session", purchase);

        return new ModelAndView("redirect:/user/");
    }

    @RequestMapping("/view-cart")
    public ModelAndView viewCart(HttpSession session) {

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        ModelAndView modelAndView = new ModelAndView("cart");

        modelAndView.addObject("purchase", purchase);

        return modelAndView;
    }

    @RequestMapping("/purchase")
    public String purchase(HttpSession session) {

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        purchase.setUser((User) session.getAttribute("user-session"));

        purchase.setDate(new Date());

        purchaseService.save(purchase);

        session.removeAttribute("purchase-session");


        return "confirmation";
//        return new ModelAndView("redirect:/user/");
    }

}
