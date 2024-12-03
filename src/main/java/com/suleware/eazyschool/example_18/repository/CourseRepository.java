package com.suleware.eazyschool.example_18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
