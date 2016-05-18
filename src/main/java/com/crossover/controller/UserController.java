package com.crossover.controller;

import com.crossover.domain.User;
import com.crossover.domain.UserCreateForm;
import com.crossover.domain.validator.UserCreateFormValidator;
import com.crossover.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserCreateFormValidator userCreateFormValidator;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.info("User Form has errors BindingResult={} , Form={}", bindingResult, form);
            return "login";
        }
        try {
            userService.registerUser(form);
            LOGGER.info("User Registered success, Form={}", form);
            // TODO Authenticate registered user

        } catch (DataIntegrityViolationException e) {
            LOGGER.info("Error while registering user, Form={}, exception:", form, e.getMessage());
            bindingResult.reject("username.exists", "This username already exists");
            return "login";
        }
        return "redirect:/";
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(@RequestParam Optional<String> error,
                               Model model) {
        model.addAttribute("error", error);
        return "login";
    }

}
