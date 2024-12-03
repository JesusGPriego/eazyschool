package com.suleware.eazyschool.example_18.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  private String name;
  private String fees;

  @ManyToMany(mappedBy = "courses",
      fetch = FetchType.LAZY,
      cascade = CascadeType.PERSIST)
  private Set<Person> persons;

  public Course() {
    this.persons = new HashSet<>();
  }
}
