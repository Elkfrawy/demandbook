package com.crossover.service;

import com.crossover.domain.Book;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface BookService {

    List<Book> getAllBooks();
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByDesc(String keyword);
    List<Book> searchBooksByTitleOrDesc(String keyword);
    List<Book> searchBooksByPublisher(String publisher);
    Book getBookById(String id);
    List<Book> getAllBooks(List<String> bookids);
}
