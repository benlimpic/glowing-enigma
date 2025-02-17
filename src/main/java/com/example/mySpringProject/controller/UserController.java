package com.example.mySpringProject.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @PutMapping("/{username}")
  @ResponseStatus(HttpStatus.NO_CONTENT)



  private void copyUserInfoFrom(User updatedUser, User existingUser) {

  }

  private void validateUser(User user) {
    validateUsername(user.getUsername());

    Optional<User> optionalUser = this.userRepository.findUserByUsername(user.getUsername());
    if (optionaleUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
  }

  private void validateUsername(String username) {
    if (ObjectUtils.isEmpty(username)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
