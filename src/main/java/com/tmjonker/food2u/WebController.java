package com.tmjonker.food2u;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute Customer customer, Model model) {
        model.addAttribute("newCustomer", customer);
        return "result";
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

}