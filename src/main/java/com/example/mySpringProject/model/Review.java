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
  private String review;

  private Integer peanutScore;
  private Integer eggScore;
  private Integer dairyScore;

  private ReviewStatus status;
}
