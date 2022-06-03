package com.tmjonker.food2u.services;

import com.tmjonker.food2u.forms.NewRestaurantForm;
import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {

        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant checkExistsAndAddRestaurant(NewRestaurantForm newRestaurantForm) {

        Restaurant newRestaurant = newRestaurantForm.toRestaurant();
        if (!restaurantRepository.existsByNameAndPhoneNumber(newRestaurant.getName(),
                newRestaurant.getPhoneNumber())) {
            return restaurantRepository.save(newRestaurant);
        } else {
            return null;
        }
    }
}
