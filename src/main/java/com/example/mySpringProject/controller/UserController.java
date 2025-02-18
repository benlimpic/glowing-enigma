package com.example.mySpringProject.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

  
  @GetMapping("/{username}")
  public User getUser(
    @PathVariable("username") String username
  ) {
    validateUsername(username);

    Optional<User> optionalUser = this.userRepository.findUserByUsername(username);
    if(!optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    User foundUser = optionalUser.get();
    foundUser.setId(null);
    return foundUser;
  }


  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public void newUser(
    @RequestBody User user
  ) {
    validateUser(user);
    this.userRepository.save(user);
  }


  @PutMapping("/{username}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateUserInfo(@PathVariable String username, @RequestBody User updatedUser) {
    validateUsername(username);

    Optional<User> optionalExistingUser = this.userRepository.findUserByUsername(username);
    if (optionalExistingUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    User existingUser = optionalExistingUser.get();

    copyUserInfoFrom(updatedUser, existingUser);
    userRepository.save(existingUser);
  }


  private void copyUserInfoFrom(User updatedUser, User existingUser) {

    if (ObjectUtils.isEmpty(updatedUser.getUsername())) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    if(!ObjectUtils.isEmpty(updatedUser.getCity())) {
      existingUser.setCity(updatedUser.getCity());
    }

    if(!ObjectUtils.isEmpty(updatedUser.getState())) {
      existingUser.setState(updatedUser.getState());
    }

    if(!ObjectUtils.isEmpty(updatedUser.getZipcode())) {
      existingUser.setZipcode(updatedUser.getZipcode());
    }

    if(!ObjectUtils.isEmpty(updatedUser.getPeanutWatch())) {
      existingUser.setPeanutWatch(updatedUser.getPeanutWatch());
    }

    if(!ObjectUtils.isEmpty(updatedUser.getEggWatch())) {
      existingUser.setEggWatch(updatedUser.getEggWatch());
    }

    if(!ObjectUtils.isEmpty(updatedUser.getDairyWatch())) {
      existingUser.setEggWatch(updatedUser.getEggWatch());
    }
  }


  private void validateUser(User user) {
    validateUsername(user.getUsername());

    Optional<User> optionalUser = this.userRepository.findUserByUsername(user.getUsername());
    if (optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
  }


  private void validateUsername(String username) {
    if (ObjectUtils.isEmpty(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
