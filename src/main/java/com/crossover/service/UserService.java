package com.crossover.service;

import com.crossover.domain.User;
import com.crossover.domain.UserCreateForm;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface UserService {

    public boolean registerUser(UserCreateForm userCreateForm);
    public boolean loginUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);

    public User getUserById(long id);
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public List<User> getAllUsers();

    boolean deleteUserById(long id);
}
