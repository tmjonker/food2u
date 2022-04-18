package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.entities.restaurant.NewRestaurantForm;
import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.entities.restaurant.RestaurantRepository;
import com.tmjonker.food2u.entities.user.ReturningUserForm;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Controller
public class AdminController {


    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    @Autowired
    public AdminController(UserRepository userRepository, RestaurantRepository restaurantRepository) {

        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/admin")
    public String adminForm(HttpServletRequest request, @ModelAttribute NewRestaurantForm newRestaurantForm,
                             Model model) {
        // gets current logged in user.
        Principal principal = request.getUserPrincipal();
        User user = userRepository.findByEmail(principal.getName());
        user.setLogins(user.getLogins() + 1);
        userRepository.save(user);

        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/admin")
    public String adminSubmit(@ModelAttribute NewRestaurantForm newRestaurantForm, @ModelAttribute User user,
                              BindingResult bindingResult, Model model,
                              @RequestParam(name="success", required = false, defaultValue = "false") String success,
                              @RequestParam(name="exists", required = false, defaultValue = "false") String exists) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            Restaurant newRestaurant = newRestaurantForm.toRestaurant();
            if (!restaurantRepository.existsByNameAndPhoneNumber(newRestaurant.getName(),
                    newRestaurant.getPhoneNumber())) {
                restaurantRepository.save(newRestaurant);
                model.addAttribute("user", user);
                return "redirect:admin?success=true";
            } else {
                model.addAttribute("newRestaurantForm", newRestaurantForm);
                model.addAttribute("user", user);

                ArrayList words = new ArrayList<>(Arrays.asList("Hello", "World"));

                Function<Integer, Integer> squareLamba = x -> x*x;
                return "redirect:admin?exists=true";
            }
        }
    }

    @GetMapping({"/admin?success", "/admin?exists"})
    public String adminSuccessSubmit(@ModelAttribute NewRestaurantForm newRestaurantForm, @ModelAttribute User user,
                                     @RequestParam String id, Model model) {
        model.addAttribute("user", user);
        return "admin";
    }
}
