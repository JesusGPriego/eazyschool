package com.suleware.eazyschool.example_18.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

  PersonRepository personRepository;

  public DashboardController(
      PersonRepository personRepository
  ) {
    this.personRepository = personRepository;
  }

  @RequestMapping("/dashboard")
  public String displayDashboard(
      Model model,
      Authentication authentication,
      HttpSession session
  ) {
    Person person = personRepository.findByEmail(authentication.getName());
    model.addAttribute("username", person.getName());
    model.addAttribute("roles", authentication.getAuthorities().toString());
    if (null != person.getEazyClass()
        && null != person.getEazyClass().getName()) {
      model.addAttribute("enrolledClass", person.getEazyClass().getName());
    }
    session.setAttribute("loggedInPerson", person);
    return "dashboard.html";
  }
}
