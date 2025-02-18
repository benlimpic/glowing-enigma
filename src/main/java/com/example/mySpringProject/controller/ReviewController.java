package com.example.mySpringProject.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.mySpringProject.model.Restaurant;
import com.example.mySpringProject.model.Review;
import com.example.mySpringProject.model.ReviewStatus;
import com.example.mySpringProject.model.User;
import com.example.mySpringProject.repository.RestaurantRepository;
import com.example.mySpringProject.repository.ReviewRepository;
import com.example.mySpringProject.repository.UserRepository;


@RequestMapping("/reviews")
@RestController
public class ReviewController {

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;

  public ReviewController(
    ReviewRepository reviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
    this.reviewRepository = reviewRepository;
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void newUserReview(@RequestBody Review review) {
    validateUserReview(review);

    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
    if (optionalRestaurant.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    review.setStatus(ReviewStatus.PENDING);
    reviewRepository.save(review);
  }
  
  private void validateUserReview(Review review) {
    if (ObjectUtils.isEmpty(review.getSubmittedBy())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (ObjectUtils.isEmpty(review.getRestaurantId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (ObjectUtils.isEmpty(review.getPeanutScore()) && 
      ObjectUtils.isEmpty(review.getPeanutScore()) && 
      ObjectUtils.isEmpty(review.getPeanutScore())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
      }

    Optional<User> optionalUser = userRepository.findUserByUsername(review.getSubmittedBy());
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }


}
