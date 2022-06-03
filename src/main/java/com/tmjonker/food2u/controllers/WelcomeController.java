package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import com.tmjonker.food2u.services.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WelcomeController {

    private UserServiceDetails userServiceDetails;

    @Autowired
    public WelcomeController(UserServiceDetails userServiceDetails) {

        this.userServiceDetails = userServiceDetails;
    }

    @GetMapping("/welcome")
    public String welcomeForm(HttpServletRequest request, Model model) {

        // gets the user that is currently logged in.
        Principal principal = request.getUserPrincipal();
        User user = (User) userServiceDetails.loadUserByUsername(principal.getName());
        if (!user.getRole().equals("ADMIN")) {
            userServiceDetails.incrementUserLogins(principal.getName());
        }
        // model attributes that are passed to the thymeleaf templates.
        model.addAttribute("user", user);

        if (user.getRole().equals("ADMIN")) {
            if (user.getLogins() == 0) // if admin user has never logged in before, redirect to password change page.
                return "redirect:/change_password_admin";
            else // if admin user has logged in before, redirect to admin page.
                return "redirect:/admin";
        } else {

            return "welcome";
        }
    }
}
