package com.crossover.service.imp;

import com.crossover.domain.Book;
import com.crossover.repository.BookRepository;
import com.crossover.service.BookService;
import com.crossover.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleLike(title);
    }

    @Override
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorsLikeIgnoreCase(author);
    }

    @Override
    public List<Book> searchBooksByDesc(String keyword) {
        return null;
    }

    @Override
    public List<Book> searchBooksByTitleOrDesc(String keyword) {
        return bookRepository.findByDescriptionLikeOrTitleLikeIgnoreCase(keyword, keyword);
    }

    @Override
    public List<Book> searchBooksByPublisher(String publisher) {
        return bookRepository.findByPublisherLike(publisher);
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getAllBooks(List<String> bookids) {
        return new ArrayList<>(Util.makeCollection(bookRepository.findAll(bookids)));
    }
}
