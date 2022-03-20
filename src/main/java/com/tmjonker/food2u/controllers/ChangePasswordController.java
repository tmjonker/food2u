package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/change_password")
    public String signInform(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("returningUser", returningUserForm);

        return "change_password";
    }

    @PostMapping("/change_password")
    public String signInSubmit(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("user", returningUserForm);
        return "welcome";
    }
}
