package com.tmjonker.food2u.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Integer id) {

        super("Could not find restaurant " + id);
    }
}
