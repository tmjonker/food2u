package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.restaurant.NewRestaurantForm;
import com.tmjonker.food2u.entities.restaurant.RestaurantRepository;
import com.tmjonker.food2u.entities.user.NewUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/admin")
    public String adminForm(@ModelAttribute NewRestaurantForm newRestaurantForm, Model model) {
        model.addAttribute("newRestaurantForm", newRestaurantForm);
        return "admin";
    }
}
