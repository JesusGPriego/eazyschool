package com.suleware.eazyschool.example_18.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suleware.eazyschool.example_18.model.EazyClass;

public interface EazyClassRepository extends JpaRepository<EazyClass, Long> {
  
}
