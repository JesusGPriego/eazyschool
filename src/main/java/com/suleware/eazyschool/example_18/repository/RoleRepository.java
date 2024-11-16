package com.suleware.eazyschool.example_18.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles, Long> {
  Optional<Roles> getByRoleName(
      String roleName
  );
}
