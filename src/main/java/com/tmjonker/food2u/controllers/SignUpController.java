package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.entities.user.UserRepository;
import com.tmjonker.food2u.logging.Food2uLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;

@Controller
public class SignUpController {

    private static final Logger LOGGER = Food2uLogger.setUp(SignUpController.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("newUser", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute User newUser, Model model, RedirectAttributes redirectAttributes) {

        if (!userRepository.existsByEmail(newUser.getEmail())) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
            model.addAttribute("newUser", newUser);
            return "result";
        } else {
            ReturningUserForm returningUserForm = new ReturningUserForm(newUser.getEmail(), true);
            redirectAttributes.addFlashAttribute("returningUserForm", returningUserForm);
            return "redirect:/sign-in";
        }
    }
}