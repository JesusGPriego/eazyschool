package com.suleware.eazyschool.example_18.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
  @Query("SELECT c FROM Contact c WHERE c.status = :status")
  // @Query(value = "SELECT * FROM contact_msg c WHERE c.status =
  // :status",nativeQuery = true)
  Page<Contact> findByStatusWithQuery(
      @Param("status")
      String status,
      Pageable pageable
  );

  @Query("SELECT c FROM Contact c WHERE c.status = :status")
  List<Contact> findByStatus(
      @Param("status")
      String status
  );
}
