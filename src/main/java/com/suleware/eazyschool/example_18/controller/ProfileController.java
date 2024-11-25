package com.suleware.eazyschool.example_18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suleware.eazyschool.example_18.model.Address;
import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.model.Profile;
import com.suleware.eazyschool.example_18.repository.PersonRepository;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {

  private PersonRepository personRepository;

  public ProfileController(
      PersonRepository personRepository
  ) {
    this.personRepository = personRepository;
  }

  @GetMapping("/displayProfile")
  public ModelAndView displayProfile(
      Model model,
      HttpSession httpSession
  ) {
    Person person = (Person) httpSession.getAttribute("loggedInPerson");
    Profile profile = new Profile();
    profile.setName(person.getName());
    profile.setMobileNumber(person.getMobileNumber());
    profile.setEmail(person.getEmail());
    if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
      profile.setAddress1(person.getAddress().getAddress1());
      profile.setAddress2(person.getAddress().getAddress2());
      profile.setCity(person.getAddress().getCity());
      profile.setState(person.getAddress().getState());
      profile.setZipCode(person.getAddress().getZipCode());
    }
    ModelAndView modelAndView = new ModelAndView("profile.html");
    modelAndView.addObject("profile", profile);
    return modelAndView;
  }

  @PostMapping(value = "/updateProfile")
  public String updateProfile(
      @Valid
      @ModelAttribute("profile")
      Profile profile,
      Errors errors,
      HttpSession session
  ) {
    if (errors.hasErrors()) {
      return "profile.html";
    }
    Person person = (Person) session.getAttribute("loggedInPerson");
    person.setName(profile.getName());
    person.setEmail(profile.getEmail());
    person.setMobileNumber(profile.getMobileNumber());
    if (person.getAddress() == null
        || person.getAddress().getAddressId() <= 0) {
      person.setAddress(new Address());
    }
    person.getAddress().setAddress1(profile.getAddress1());
    person.getAddress().setAddress2(profile.getAddress2());
    person.getAddress().setCity(profile.getCity());
    person.getAddress().setState(profile.getState());
    person.getAddress().setZipCode(profile.getZipCode());
    Person savedPerson = personRepository.save(person);
    session.setAttribute("loggedInPerson", savedPerson);
    return "redirect:/displayProfile";
  }
}
