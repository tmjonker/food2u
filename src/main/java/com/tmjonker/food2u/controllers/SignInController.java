package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SignInController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-in")
    public String signInform(@ModelAttribute("returningUserForm") ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("user", returningUserForm);
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signInSubmit(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

            return "result";
    }
}
