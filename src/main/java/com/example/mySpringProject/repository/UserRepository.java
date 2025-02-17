package com.example.mySpringProject.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findUserByUsername(String username);
}
