package com.example.mySpringProject.controller;

import com.example.mySpringProject.model.Restaurant;
import com.example.mySpringProject.repository.RestaurantRepository;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.regex.Pattern;


@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
  private final RestaurantRepository restaurantRepository;
  private final Pattern zipcodePattern = Pattern.compile("d{5}");

  public RestaurantController(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurant newRestaurant(@RequestBody Restaurant restaurant) {
    validateNewRestaurant(restaurant);
    return this.restaurantRepository.save(restaurant);
  }

  @GetMapping("/{id}")
  public Restaurant getRestaurant(@PathVariable Long id) {
    Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
    if (restaurant.isPresent()) {
      return restaurant.get();
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/")
  public Iterable<Restaurant> getAllResstaurants() {
    return this.restaurantRepository.findAll();
  }

  @GetMapping("/search")
  public Iterable<Restaurant> searchRestaurants(@RequestParam String zipcode, @RequestParam String allergy) {
    validateZipcode(zipcode);

    Iterable<Restaurant> restaurants = Collections.EMPTY_LIST;
    if (allergy.equalsIgnoreCase("peanut")) {
      restaurants = restaurantRepository.findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(zipcode);
    }
    else if (allergy.equalsIgnoreCase("dairy")) {
      restaurants = restaurantRepository.findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(zipcode);
    }
    else if (allergy.equalsIgnoreCase("egg")) {
      restaurants = restaurantRepository.findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(zipcode);
    }
    else {}

    return restaurants;
  }

  private void validateNewRestaurant(Restaurant restaurant) {
    if (ObjectUtils.isEmpty(restaurant.getName())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    validateZipcode(restaurant.getZipcode());

    Optional<Restaurant> existingRestaurant = restaurantRepository.findRestaurantsByNameAndZipcode(restaurant.getName(),
        restaurant.getZipcode());
    if (existingRestaurant.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
  }

  private void validateZipcode(String zipcode) {
    if (!zipcodePattern.matcher(zipcode).matches()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
