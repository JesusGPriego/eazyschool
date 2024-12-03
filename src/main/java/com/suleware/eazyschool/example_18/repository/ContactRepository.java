package com.suleware.eazyschool.example_18.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
  List<Contact> findByStatus(
      String status
  );

  Page<Contact> findByStatus(
      String status,
      Pageable pageable
  );
}
