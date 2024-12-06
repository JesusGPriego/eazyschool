package com.suleware.eazyschool.example_18.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.suleware.eazyschool.example_18.model.Roles;

@Repository
@RepositoryRestResource(exported = false)
public interface RoleRepository extends CrudRepository<Roles, Long> {
  Optional<Roles> getByRoleName(
      String roleName
  );
}
