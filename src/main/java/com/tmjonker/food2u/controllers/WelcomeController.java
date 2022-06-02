package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WelcomeController {

    private UserRepository userRepository;

    @Autowired
    public WelcomeController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/welcome")
    public String welcomeForm(HttpServletRequest request, Model model) {

        // gets the user that is currently logged in.
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByEmail(principal.getName());
        if (!user.getRole().equals("ADMIN"))
            user.setLogins(user.getLogins() + 1); // Keeps track of the number of logins for the user.
        userRepository.save(user);
        // model attributes that are passed to the thymeleaf templates.
        model.addAttribute("user", user);

        if (user.getRole().equals("ADMIN")) {
            if (user.getLogins() == 0) // if admin user has never logged in before, redirect to password change page.
                return "redirect:/change_password_admin";
            else // if admin user has logged in before, redirect to admin page.
                return "redirect:/admin";
        } else {
            // model attributes that are passed to the thymeleaf templates.
            model.addAttribute("user", user);
            return "welcome";
        }
    }
}
