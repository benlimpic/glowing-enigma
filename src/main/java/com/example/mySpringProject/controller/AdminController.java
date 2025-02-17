package com.example.mySpringProject.controller;

import com.example.mySpringProject.repository.RestaurantRepository;
import com.example.mySpringProject.repository.ReviewRepository;
import com.example.mySpringProject.repository.UserRepository;


public class AdminController {
  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;

  public AdminController(ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
    this.reviewRepository = reviewRepository;
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
  }
  
}
