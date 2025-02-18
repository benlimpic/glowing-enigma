package com.example.mySpringProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.Review;
import com.example.mySpringProject.model.ReviewStatus;


public interface ReviewRepository extends CrudRepository<Review, Long> {
  List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);
  List<Review> findReviewsByStatus(ReviewStatus reviewStatus);
}
