package com.example.mySpringProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

}
