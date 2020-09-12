package com.tmjonker.food2u.entities.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    boolean existsByEmail(String email);

}
