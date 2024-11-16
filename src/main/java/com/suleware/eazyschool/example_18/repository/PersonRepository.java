package com.suleware.eazyschool.example_18.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
  
}
