package com.example.mySpringProject.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
  Optional<Restaurant> findRestaurantsByNameAndZipcode(String name, String zipcode);
  Iterable<Restaurant> findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipcode);
  Iterable<Restaurant> findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipcode);
  Iterable<Restaurant> findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(String zipcode);
}
