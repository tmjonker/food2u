package com.tmjonker.food2u.controllers;

import com.tmjonker.food2u.forms.NewRestaurantForm;
import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.entities.user.User;
import com.tmjonker.food2u.services.RestaurantService;
import com.tmjonker.food2u.services.UserServiceDetails;
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

@Controller
public class AdminController {


    private UserServiceDetails userServiceDetails;

    private RestaurantService restaurantService;

    @Autowired
    public AdminController(UserServiceDetails userServiceDetails, RestaurantService restaurantService) {

        this.userServiceDetails = userServiceDetails;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/admin")
    public String adminForm(HttpServletRequest request, @ModelAttribute NewRestaurantForm newRestaurantForm,
                             Model model) {
        // gets current logged in user.
        Principal principal = request.getUserPrincipal();
        User user = userServiceDetails.incrementUserLogins(principal.getName());

        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/admin")
    public String adminSubmit(@ModelAttribute NewRestaurantForm newRestaurantForm, @ModelAttribute User user,
                              BindingResult bindingResult, Model model,
                              @RequestParam(name="success", required = false, defaultValue = "false") String success,
                              @RequestParam(name="exists", required = false, defaultValue = "false") String exists) {

        if (bindingResult.hasErrors()) {
            return "admin";
        } else {
            Restaurant restaurant = restaurantService.checkExistsAndAddRestaurant(newRestaurantForm);
            if (restaurant != null) {
                return "redirect:admin?success=true";
            } else {

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
