package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.customer.CustomerRepository;
import com.tmjonker.food2u.entities.customer.ReturningCustomerForm;
import com.tmjonker.food2u.entities.keyset.KeysetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class SignInController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KeysetRepository keysetRepository;

    @GetMapping("/sign-in")
    public String signInform(@ModelAttribute ReturningCustomerForm returningCustomerForm, Model model) {
        model.addAttribute("customerForm", returningCustomerForm);
        return "sign-in";
    }
}
