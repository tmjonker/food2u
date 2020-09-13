package com.tmjonker.food2u.security.services;

import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserServiceDetails implements UserDetailsService {

    private UserRepository userRepository;

    private UserDetails user;

    DatabaseUserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        user = userRepository.findByEmail(email);
        return user;
    }
}