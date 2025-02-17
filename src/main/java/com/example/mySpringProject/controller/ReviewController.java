package com.example.mySpringProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mySpringProject.repository.RestaurantRepository;
import com.example.mySpringProject.repository.ReviewRepository;
import com.example.mySpringProject.repository.UserRepository;



@RequestMapping("/reviews")
@RestController
public class ReviewController {

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;

  public ReviewController(ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
    this.reviewRepository = reviewRepository;
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
  }
  
}
