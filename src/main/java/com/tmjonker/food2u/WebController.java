package com.tmjonker.food2u;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public @ResponseBody String signUpSubmit(@RequestParam String email, @RequestParam String password,
                                             @RequestParam String firstName, @RequestParam String middleInitial,
                                             @RequestParam String lastName, @RequestParam String address,
                                             @RequestParam String address2, @RequestParam String city,
                                             @RequestParam String state, @RequestParam Integer zipCode) {

        CustomerForm customerForm = new CustomerForm();
        customerForm.setEmail(email);
        customerForm.setPassword(password);
        customerForm.setFirstName(firstName);
        customerForm.setMiddleInitial(middleInitial);
        customerForm.setLastName(lastName);
        customerForm.setAddress(address);
        customerForm.setAddress2(address2);
        customerForm.setCity(city);
        customerForm.setState(state);
        customerForm.setZipCode(zipCode);

        customerRepository.save(customerForm);

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