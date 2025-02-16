package com.example.mySpringProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private final String username;

  private String city;
  private String state;
  private String zipcode;

  private Boolean peanutWatch;
  private Boolean eggWatch;
  private Boolean dairyWatch;
  
}
