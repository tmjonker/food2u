package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.restaurant.NewRestaurantForm;
import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String adminForm(HttpServletRequest request, @ModelAttribute NewRestaurantForm newRestaurantForm,
                             Model model) {

        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByEmail(principal.getName());
        user.setLogins(user.getLogins() + 1);
        userRepository.save(user);

        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/admin")
    public String adminSubmit(@ModelAttribute NewRestaurantForm newRestaurantForm, BindingResult bindingResult,
                              Model model) {

        model.addAttribute("user", newRestaurantForm);

        return "welcome";
    }
}
