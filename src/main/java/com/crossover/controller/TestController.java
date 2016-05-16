package com.crossover.controller;

import com.crossover.domain.User;
import com.crossover.domain.UserMongo;
import com.crossover.repository.BookRepository;
import com.crossover.repository.UserMongoRepsitory;
import com.crossover.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ayman on 5/14/2016.
 */

@RestController
public class TestController {
/*
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMongoRepsitory userMongoRepsitory;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/books")
    public String listAllBooks() {
        return bookRepository.findAll().toString();
//        return "Books";
    }

    @RequestMapping("/users")
    public String listAllUsers() {
        return userRepository.findAll().toString();
    }

    @RequestMapping("/adduser")
    public String addUser() {

        return "User added";
    }

    @RequestMapping("/usersmongo")
    public String listAllUsersMongo() {
        return userMongoRepsitory.findAll().toString();
    }
    */
}
