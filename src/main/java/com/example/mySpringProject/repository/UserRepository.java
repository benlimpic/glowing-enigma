package com.example.mySpringProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.mySpringProject.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
  List<User> findAll();
  User findById(long id);
  

}
