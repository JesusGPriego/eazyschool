package com.suleware.eazyschool.example_18.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Holiday;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {
  
}
