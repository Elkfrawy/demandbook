package com.crossover.controller;

import com.crossover.domain.Book;
import com.crossover.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */


@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/book")
    public String searchBook(@RequestParam(required = false) String q,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) String publisher,
                             Model model) {
        List<Book> books;
        if (!StringUtils.isEmpty(q))
            books = bookService.searchBooksByTitleOrDesc(q);
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
    public String bookDetails(@PathVariable() String id) {
        Book book = bookService.getBookById(id);

        return "bookdetails";
    }

    @RequestMapping("/")
    public String index(Model model) {
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("books", books);
//        return "books";
        return "index";
    }

}
