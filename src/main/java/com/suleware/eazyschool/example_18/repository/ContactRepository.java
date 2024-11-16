package com.suleware.eazyschool.example_18.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
  List<Contact> findByStatus(
      String status
  );
}
