package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.ChangePasswordForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.repositories.UserRepository;
import com.tmjonker.food2u.services.DatabaseUserDetailsPasswordService;
import com.tmjonker.food2u.services.DatabaseUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ChangePasswordController {

    private UserRepository userRepository;

    private DatabaseUserDetailsPasswordService passwordService;

    private DatabaseUserServiceDetails userServiceDetails;

    @Autowired
    public ChangePasswordController (UserRepository userRepository, DatabaseUserDetailsPasswordService passwordService,
                                     DatabaseUserServiceDetails userServiceDetails) {

        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.userServiceDetails = userServiceDetails;
    }

    @GetMapping("/change_password_admin")
    public String cpaForm(@ModelAttribute ChangePasswordForm changePasswordForm, HttpServletRequest request, Model model) {

        // gets current logged in user.
        Principal principal = request.getUserPrincipal();
        User returningUser = userRepository.findByEmail(principal.getName());

        // model attributes that are passed to the thymeleaf templates.
        model.addAttribute("changePasswordForm", changePasswordForm);
        model.addAttribute("user", returningUser);

        return "change_password_admin";
    }

    @PostMapping("/change_password_admin")
    public String cpaSubmit(@ModelAttribute ChangePasswordForm changePasswordForm, Model model) {

        User returningUser = userRepository.findByEmail(changePasswordForm.getUsername());

        if (!changePasswordForm.getPassword1().equals(changePasswordForm.getPassword2())) {
            changePasswordForm.setPasswordsMatch(false);

            // model attributes that are passed to the thymeleaf templates.
            model.addAttribute("user", returningUser);
            model.addAttribute("changePasswordForm", changePasswordForm);
            return "change_password_admin";
        } else {

            UserDetails userDetails = userServiceDetails.loadUserByUsername(returningUser.getEmail());
            passwordService.updatePassword(userDetails, changePasswordForm.getPassword1());

            // model attributes that are passed to the thymeleaf templates.
            model.addAttribute("user", returningUser);
            model.addAttribute("changePasswordForm", changePasswordForm);
            return "redirect:/admin";
        }
    }
}
