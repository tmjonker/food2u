package com.tmjonker.food2u.repositories;

import com.tmjonker.food2u.entities.restaurant.Restaurant;
import com.tmjonker.food2u.entities.user.User;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);

    Restaurant findByNameAndPhoneNumber(String name, String phoneNumber);

}
