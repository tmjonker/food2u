package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.customer.Customer;
import com.tmjonker.food2u.entities.customer.NewCustomerForm;
import com.tmjonker.food2u.entities.customer.CustomerRepository;
import com.tmjonker.food2u.encryption.PasswordEncryptor;
import com.tmjonker.food2u.entities.customer.ReturningCustomerForm;
import com.tmjonker.food2u.entities.keyset.Keyset;
import com.tmjonker.food2u.entities.keyset.KeysetRepository;
import com.tmjonker.food2u.logging.Food2uLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SignUpController {

    private static final Logger LOGGER = Food2uLogger.setUp(SignUpController.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KeysetRepository keysetRepository;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("newCustomerForm", new NewCustomerForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute NewCustomerForm newCustomerForm, Model model) {

        Customer customer = newCustomerForm.toCustomer();
        Keyset keyset = new Keyset();

        try {
            PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
            customer.setPassword(passwordEncryptor.encryptPassword(newCustomerForm.getPassword(), newCustomerForm.getEmail()));
            keyset.setKeysetHandle(passwordEncryptor.getKeysetHandle().toString());
        } catch (GeneralSecurityException ex) {
            LOGGER.log(Level.SEVERE, "Error while trying to encrypt password.", ex);
            return "sign-up";
        }

        if (!customerRepository.existsByEmail(newCustomerForm.getEmail())) {
            customerRepository.save(customer);
            keyset.setId(customer.getId());
            keysetRepository.save(keyset);
            model.addAttribute("newCustomerForm", customer);
            return "result";
        } else {
            ReturningCustomerForm returningCustomerForm = new ReturningCustomerForm();
            returningCustomerForm.setAlreadyExists(true);
            returningCustomerForm.setEmail(newCustomerForm.getEmail());
            model.addAttribute("returningCustomerForm", returningCustomerForm);
            return "sign-in";
        }
    }
}