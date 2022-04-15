package com.tmjonker.food2u.restcontrollers;

import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.entities.restaurant.RestaurantRepository;
import com.tmjonker.food2u.exceptions.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {

        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/restaurants")
    private List<Restaurant> all() {

        return Streamable.of(restaurantRepository.findAll()).toList();
    }

    @PostMapping("/restaurants")
    private Restaurant newRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    @GetMapping("/restaurants/{id}")
    private Restaurant one(@PathVariable Integer id) {

        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @PutMapping("/restaurants/{id}")
    private Restaurant replaceRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Integer id) {

        return restaurantRepository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    restaurant.setAddress(newRestaurant.getAddress());
                    restaurant.setAddress2(newRestaurant.getAddress2());
                    restaurant.setCity(newRestaurant.getCity());
                    restaurant.setState(newRestaurant.getState());
                    restaurant.setZipCode(newRestaurant.getZipCode());
                    restaurant.setCategory(newRestaurant.getCategory());
                    restaurant.setImgPath(newRestaurant.getImgPath());
                    return restaurantRepository.save(restaurant);
                })
                .orElseGet(() -> {
                    newRestaurant.setId(id);
                    return restaurantRepository.save(newRestaurant);
                });
    }

    @DeleteMapping("/restaurants/{id}")
    private void deleteRestaurant(@PathVariable Integer id) {

        restaurantRepository.deleteById(id);
    }
}
