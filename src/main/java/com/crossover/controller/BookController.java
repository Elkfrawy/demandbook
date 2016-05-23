package com.crossover.controller;

import com.crossover.domain.Book;
import com.crossover.domain.CurrentUser;
import com.crossover.domain.User;
import com.crossover.service.BookService;
import com.crossover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */


@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @RequestMapping("/book")
    public String searchBook(@RequestParam(required = false) String q,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String publisher,
                             Model model) {
        List<Book> books;
        if (!StringUtils.isEmpty(q))
            books = bookService.searchBooksByAny(q);
        else if (!StringUtils.isEmpty(author))
            books = bookService.searchBooksByAuthor(author);
        else if (!StringUtils.isEmpty(publisher))
            books = bookService.searchBooksByPublisher(publisher);
        else
            books = bookService.getAllBooks();

        // TODO Filter books for current users

        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping("/book/{id}")
    public String getBookDetails(@PathVariable() String id,
                                 Model model) {
        Book book = bookService.getBookById(id);
        // Check if current user demanded this book
        User currentUser = userService.getCurrentUser();
        boolean demanded = false;
        if (currentUser.hasDemandForBook(book))
            demanded = true;

        model.addAttribute("book", book);
        model.addAttribute("hasdemand", demanded);
        return "book";
    }

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

//    private User getCurrentUserFromDB(Authentication authentication) {
//
//    }


}
