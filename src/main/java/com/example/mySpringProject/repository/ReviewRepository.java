package com.example.mySpringProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
