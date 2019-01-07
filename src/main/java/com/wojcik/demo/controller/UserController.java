package com.wojcik.demo.controller;

import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.PurchaseDetails;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.BookService;
import com.wojcik.demo.service.PurchaseService;
import com.wojcik.demo.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        modelAndView.addObject("quantity", new Integer(1));
        modelAndView.addObject("bookId", new Integer(-1));

        return modelAndView;
    }

    @RequestMapping("/add-to-cart")
    public ModelAndView addToCart(@RequestParam("bookId") int bookId,
                                  @RequestParam("quantity") int quantity,
                                  HttpSession session) {

        Book book = bookService.getById(bookId);

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        PurchaseDetails purchaseDetails =
                new PurchaseDetails(purchase, book, quantity);


        purchase.addDetails(purchaseDetails);

        session.setAttribute("purchase-session", purchase);

        return new ModelAndView("redirect:/user/");
    }

    @RequestMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam("details") int place, HttpSession session) {

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        purchase.removeDetails(place);

        return "redirect:/user/view-cart";
    }

    @RequestMapping("/view-cart")
    public ModelAndView viewCart(HttpSession session) {

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");

        ModelAndView modelAndView = new ModelAndView("cart");

        modelAndView.addObject("purchase", purchase);

        return modelAndView;
    }

    @RequestMapping("/details")
    public ModelAndView showDetailsForm(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView("details");

        modelAndView.addObject("purchase", new Purchase());

        return modelAndView;
    }

    @RequestMapping("/details/process")
    public String processDetails(@Valid @ModelAttribute("purchase") Purchase purchaseModel,
                                       BindingResult result,
                                       HttpSession session, Model model) {

        model.addAttribute("result", result);

        if(result.hasErrors()) return "details";

        Purchase purchase = (Purchase) session.getAttribute("purchase-session");
            purchase.setPostalCode(purchaseModel.getPostalCode());
            purchase.setCity(purchaseModel.getCity());
            purchase.setStreet(purchaseModel.getStreet());
            purchase.setHomeNumber(purchaseModel.getHomeNumber());
            purchase.setPaymentMethod(purchaseModel.getPaymentMethod());

        session.setAttribute("purchase-session", purchase);

        return "redirect:/user/purchase";
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

    @RequestMapping("/preview-purchases")
    public ModelAndView previewPurchases(HttpSession session) {

        User user = (User) session.getAttribute("user-session");

        List<Purchase> purchases = purchaseService.getPurchasesByUser(user);

        return new ModelAndView("preview-purchases", "purchases", purchases);
    }

    @RequestMapping("/view-bestsellers")
    public ModelAndView viewBestsellers() {

        List<Book> bestsellers = bookService.getBestsellers();

        ModelAndView modelAndView = new ModelAndView("bestsellers");

        modelAndView.addObject("bestsellers", bestsellers);

        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
