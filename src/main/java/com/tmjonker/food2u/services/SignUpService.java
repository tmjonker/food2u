package com.tmjonker.food2u.services;

import com.tmjonker.food2u.entities.user.NewUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
