package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/welcome")
    public String displayResult(HttpServletRequest request, Model model) {

        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByEmail(principal.getName());
        user.setLogins(user.getLogins() + 1);
        userRepository.save(user);
        model.addAttribute("user", user);

        if (user.getRole().equals("ADMIN")) {
            if (user.getLogins() == 1)
                return "redirect:/change_password_admin";
            else
                return "redirect:/admin";
        } else {
            model.addAttribute("user", user);
            return "welcome";
        }
    }
}
