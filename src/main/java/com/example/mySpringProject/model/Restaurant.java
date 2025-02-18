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
public class Restaurant {
  @Id
  @GeneratedValue()
  private Long id;

  private String name;

  private String line1;
  private String city;
  private String state;
  private String zipcode;

  private String phoneNumber;
  private String website;

  private String overallScore;
  private String peanutScore;
  private String eggScore;
  private String dairyScore;

}
