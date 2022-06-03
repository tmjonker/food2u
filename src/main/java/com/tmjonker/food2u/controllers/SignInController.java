package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.forms.ReturningUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SignInController {

    @GetMapping("/sign-in")
    public String signInform(@ModelAttribute ReturningUserForm returningUserForm, Model model) {

        model.addAttribute("returningUser", returningUserForm);

        return "sign-in";
    }
}
