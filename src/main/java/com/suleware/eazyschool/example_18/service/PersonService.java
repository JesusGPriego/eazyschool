package com.suleware.eazyschool.example_18.service;

import org.springframework.stereotype.Service;

import com.suleware.eazyschool.example_18.constants.EazySchoolConstants;
import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.model.Roles;
import com.suleware.eazyschool.example_18.repository.PersonRepository;
import com.suleware.eazyschool.example_18.repository.RoleRepository;

@Service
public class PersonService {
  private PersonRepository personRepository;
  private RoleRepository roleRepository;

  public PersonService(
      PersonRepository personRepository,
      RoleRepository roleRepository
  ) {
    this.personRepository = personRepository;
    this.roleRepository = roleRepository;
  }

  public boolean createNewPerson(
      Person person
  ) {
    boolean isSaved = false;
    Roles role =
        roleRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE).get();
    person.setRole(role);
    person = personRepository.save(person);
    if (null != person && person.getId() > 0) {
      isSaved = true;
    }
    return isSaved;
  }

}
