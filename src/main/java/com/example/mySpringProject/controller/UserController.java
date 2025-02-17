package com.example.mySpringProject.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.mySpringProject.model.User;
import com.example.mySpringProject.repository.UserRepository;

@RequestMapping("/users")
@RestController
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/")
  public List<User> getAllUsers() {
    return (List<User>) this.userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getByUserId(@PathVariable Long id) {
    Optional<User> optionalUser = this.userRepository.findById(id);

    if (!optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    } else {
      return optionalUser.get();
    }
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public User newUser(@RequestBody User user) {
    if (validateUsername(user)) {
      return this.userRepository.save(user);
    } else {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User updateUser(@RequestParam("id") Long id, @RequestBody User user) {
    Optional<User> optionalUser = this.userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    } else {
      return this.userRepository.save(user);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@RequestParam("id") Long id) {
    Optional<User> optionalUser = this.userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    } else {
      this.userRepository.delete(optionalUser.get());
    }
  }

  public Boolean validateUsername(@RequestBody User user) {
    List<User> allUsers = this.userRepository.findAll();
    for (User u : allUsers) {
      if (u.getUsername().equals(user.getUsername())) {
        return false;
      }
    }
    return true;
  }
}
