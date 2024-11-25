package com.suleware.eazyschool.example_18.service;

import org.springframework.security.crypto.password.PasswordEncoder;
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
  private PasswordEncoder passwordEncoder;

  public PersonService(
      PersonRepository personRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder
  ) {
    this.personRepository = personRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean createNewPerson(
      Person person
  ) {
    boolean isSaved = false;
    Roles role =
        roleRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE).get();
    person.setRoles(role);

    person.setPwd(passwordEncoder.encode(person.getPwd()));
    person = personRepository.save(person);
    if (null != person && person.getPersonId() > 0) {
      isSaved = true;
    }
    return isSaved;
  }

}
