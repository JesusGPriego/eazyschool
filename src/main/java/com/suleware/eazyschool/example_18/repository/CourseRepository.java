package com.suleware.eazyschool.example_18.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  /*
   * Spring Data JPA allows us to apply static sorting by adding the OrderBy
   * keyword to the method name along with the property name and sort direction
   * (Asc or Desc).
   */
  List<Course> findByOrderByNameDesc();

  /*
   * The Asc keyword is optional as OrderBy, by default, sorts the results in
   * the ascending order.
   */
  List<Course> findByOrderByName();

}
