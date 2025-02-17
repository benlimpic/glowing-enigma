package com.example.mySpringProject.controller;

import com.example.mySpringProject.model.Restaurant;
import com.example.mySpringProject.repository.RestaurantRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
  private final RestaurantRepository restaurantRepository;

  public RestaurantController(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  
}
