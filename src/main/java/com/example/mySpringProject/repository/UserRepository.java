package com.example.mySpringProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  
}
