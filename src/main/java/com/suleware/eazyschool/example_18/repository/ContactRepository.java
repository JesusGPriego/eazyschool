package com.suleware.eazyschool.example_18.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.suleware.eazyschool.example_18.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
  List<Contact> findByStatus(
      String status
  );
}
