package com.crossover.service.imp;

import com.crossover.domain.User;
import com.crossover.domain.UserCreateForm;
import com.crossover.repository.UserRepository;
import com.crossover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean registerUser(UserCreateForm userCreateForm) {
        // TODO check if username or email already registered
        User user = new User();
        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userCreateForm.getPassword()));
        user.setRole(userCreateForm.getRole());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean loginUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(long id) {
        userRepository.delete(id);
        return true;
    }
}
