package com.tmjonker.food2u.services;

import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserServiceDetails implements UserDetailsService {

    private UserRepository userRepository;

    private User user;

    @Autowired
    public DatabaseUserServiceDetails(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        user = userRepository.findByEmail(email);
        return user;
    }
}