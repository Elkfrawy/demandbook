package com.crossover.service;

import com.crossover.domain.Book;
import com.crossover.domain.User;
import com.crossover.domain.UserCreateForm;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface UserService {

    boolean registerUser(UserCreateForm userCreateForm);
    boolean loginUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);

    User getUserById(long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getAllUsers();

    boolean deleteUserById(long id);

    User getCurrentUser();

}
