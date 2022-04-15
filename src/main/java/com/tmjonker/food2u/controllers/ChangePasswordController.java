package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.ChangePasswordForm;
import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.entities.user.UserRepository;
import com.tmjonker.food2u.security.services.DatabaseUserDetailsPasswordService;
import com.tmjonker.food2u.security.services.DatabaseUserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseUserDetailsPasswordService passwordService;

    @Autowired
    private DatabaseUserServiceDetails userServiceDetails;

    @GetMapping("/change_password_admin")
    public String cpaForm(@ModelAttribute ChangePasswordForm changePasswordForm, HttpServletRequest request, Model model) {

        Principal principal = request.getUserPrincipal();
        User returningUser = userRepository.findByEmail(principal.getName());

        model.addAttribute("changePasswordForm", changePasswordForm);
        model.addAttribute("user", returningUser);

        return "change_password_admin";
    }

    @PostMapping("/change_password_admin")
    public String cpaSubmit(@ModelAttribute ChangePasswordForm changePasswordForm, Model model) {

        User returningUser = userRepository.findByEmail(changePasswordForm.getUsername());

        if (!changePasswordForm.getPassword1().equals(changePasswordForm.getPassword2())) {
            changePasswordForm.setPasswordsMatch(false);
            model.addAttribute("user", returningUser);
            model.addAttribute("changePasswordForm", changePasswordForm);
            return "change_password_admin";
        } else {

            UserDetails userDetails = userServiceDetails.loadUserByUsername(returningUser.getEmail());
            passwordService.updatePassword(userDetails, changePasswordForm.getPassword1());
            model.addAttribute("user", returningUser);
            model.addAttribute("changePasswordForm", changePasswordForm);
            return "redirect:/admin";
        }
    }
}
