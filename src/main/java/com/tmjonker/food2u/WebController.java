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
        model.addAttribute("newCustomer", new Customer());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public @ResponseBody String signUpSubmit(@RequestParam String email, @RequestParam String password,
                                             @RequestParam String firstName, @RequestParam String middleInitial,
                                             @RequestParam String lastName, @RequestParam String address,
                                             @RequestParam String address2, @RequestParam String city,
                                             @RequestParam String state, @RequestParam Integer zipCode) {

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
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