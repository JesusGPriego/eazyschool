package com.suleware.eazyschool.example_18.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.service.PersonService;

import jakarta.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {

  private PersonService personService;

  public PublicController(
      PersonService personServie
  ) {
    this.personService = personServie;
  }

  @GetMapping(value = "/register") public String displayRegisterPage(
      Model model
  ) {
    model.addAttribute("person", new Person());
    return "register.html";
  }

  @PostMapping("/createUser") public String saveUser(
      @Valid @ModelAttribute Person person,
      Errors errors
  ) {
    if (errors.hasErrors()) {
      return "register.html";
    }
    boolean isSaved = personService.createNewPerson(person);
    if (isSaved) {
      return "redirect:/login?register=true";
    } else {
      return "register.html";
    }
  }

}
