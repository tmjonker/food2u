package com.tmjonker.food2u.services;

import com.tmjonker.food2u.forms.NewUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetails implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private User user;

    @Autowired
    public UserServiceDetails(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        user = userRepository.findByEmail(email);
        return user;
    }

    public User incrementUserLogins(String email) {

        User user = userRepository.findByEmail(email);
        user.setLogins(user.getLogins() + 1);
        userRepository.save(user);

        return user;
    }

    public User createNewUser(NewUserForm newUserForm) {

        User newUser = newUserForm.toUser();
        if (!userRepository.existsByEmail(newUser.getEmail())) { // if user doesn't already exist, create new user.
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        } else {
            return null;
        }
    }
}