package com.crossover.controller;

import com.crossover.domain.User;
import com.crossover.domain.UserCreateForm;
import com.crossover.domain.validator.UserCreateFormValidator;
import com.crossover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserCreateFormValidator userCreateFormValidator;

/*
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers() {
        return userService.getAllUsers().toString();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String registerUser(@RequestParam() String username,
                               @RequestParam() String email,
                               @RequestParam() String password) {
        User user = new User(username, password, email);
        if (userService.registerUser(user)) {
            return "Registration Successes";
        } else
            return "Registration failed!";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String updateUser() {

        return "TODO";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id) {
        if (userService.deleteUserById(id))
            return "Delete Success";
        else
            return "Delete Failed";
    }*/


    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        System.out.println(form);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        try {
            userService.registerUser(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("username.exists", "This username already exists");
            return "login";
        }
        return "redirect:/";
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }


   /* @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("form", new UserCreateForm());
        return "login";
    }
*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error,
                               Model model) {
        model.addAttribute("error", error);
        return "login";
    }
}
