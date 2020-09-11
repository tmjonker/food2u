package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.customer.Customer;
import com.tmjonker.food2u.customer.CustomerForm;
import com.tmjonker.food2u.customer.CustomerRepository;
import com.tmjonker.food2u.encryption.PasswordEncryptor;
import com.tmjonker.food2u.logging.Food2uLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class WebController {

    private final Logger LOGGER = Food2uLogger.setUp(WebController.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("newCustomer", new CustomerForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public @ResponseBody String signUpSubmit(@RequestParam String email, @RequestParam String password,
                                             @RequestParam String firstName, @RequestParam String middleInitial,
                                             @RequestParam String lastName, @RequestParam String address,
                                             @RequestParam String address2, @RequestParam String city,
                                             @RequestParam String state, @RequestParam Integer zipCode) {

        Customer customer = new Customer();
        try {
            customer.setPassword(PasswordEncryptor.encryptPassword(password, email));
        } catch (GeneralSecurityException ex) {
            LOGGER.log(Level.SEVERE, "Error while trying to encrypt password.", ex);
            return "sign-up";
        }
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setMiddleInitial(middleInitial);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setAddress2(address2);
        customer.setCity(city);
        customer.setState(state);
        customer.setZipCode(zipCode);

        customerRepository.save(customer);

        return "result";
    }

    @GetMapping("/sign-in")
    public String signInform(Model model) {
        return "sign-in";
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

}