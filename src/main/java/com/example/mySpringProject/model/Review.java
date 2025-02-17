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
public class Review {

  @Id
  @GeneratedValue()
  private Long id;

  private String submittedBy;

  private Long restaurantId;
  private Float peanut;
  private Float egg;
  private Float dairy;

  private ReviewStatus status;
}
