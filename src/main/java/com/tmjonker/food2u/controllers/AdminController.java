package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.restaurant.NewRestaurantForm;
import com.tmjonker.food2u.entities.restaurant.RestaurantRepository;
import com.tmjonker.food2u.entities.user.NewUserForm;
import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String signInform(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("returningUser", returningUserForm);

        return "admin";
    }

    @PostMapping("/admin")
    public String signInSubmit(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("user", returningUserForm);
        return "welcome";
    }
}
