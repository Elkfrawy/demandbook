package com.crossover.service.imp;

import com.crossover.domain.CurrentUser;
import com.crossover.domain.User;
import com.crossover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Ayman Elkfrawy on 5/16/2016.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        return new CurrentUser(user);
    }
}
