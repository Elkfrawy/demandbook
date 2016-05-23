package com.crossover.controller;

import com.crossover.domain.Book;
import com.crossover.domain.CurrentUser;
import com.crossover.domain.Demand;
import com.crossover.domain.User;
import com.crossover.service.BookService;
import com.crossover.service.DemandService;
import com.crossover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */

@Controller
public class DemandController {

   @Autowired
    UserService userService;

    @Autowired
    DemandService demandService;

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/demand", method = RequestMethod.POST)
    public String addDemandToCurrentUser(@RequestParam() String bookid,
                                         HttpServletRequest request) {
        Demand demandToAdd = new Demand();
        User user = userService.getCurrentUser();
        demandToAdd.setBookId(bookid);
        demandToAdd.setUser(user);
        // Get book title to save it as a reference in case book get deleted from the source later
        Book book = bookService.getBookById(bookid);
        if (book != null)
            demandToAdd.setBookTitle(book.getTitle());

        demandService.addDemand(demandToAdd);

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/demand", method = RequestMethod.DELETE)
    public String removeDemandFromCurrentUser(@RequestParam() String bookid,
                                              HttpServletRequest request) {
        User user = userService.getCurrentUser();
        Demand demandToDelete = demandService.getDemandByUseridAndBookid(user.getId(), bookid);
        demandService.deleteDemand(demandToDelete);

        return "redirect:" + request.getHeader("Referer");
    }


    @RequestMapping(value = "/demand", method = RequestMethod.GET)
    public String getUserDemandBooks(Model model) {
        User user = userService.getCurrentUser();
        List<String> bookids = new ArrayList<>();
        for (Demand demand : user.getDemands()) {
            bookids.add(demand.getBookId());
        }
        List<Book> books = bookService.getAllBooks(bookids);
        model.addAttribute("books", books);

        return "mydemands";
    }

}
