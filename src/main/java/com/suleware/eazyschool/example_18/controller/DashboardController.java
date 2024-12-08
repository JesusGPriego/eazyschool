package com.suleware.eazyschool.example_18.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DashboardController {

  PersonRepository personRepository;

  @Value("${eazyschool.pageSize}")
  private int defaultPageSize;

  @Value("${eazyschool.contact.successMsg}")
  private String successMsg;

  private Environment environment;

  public DashboardController(
      PersonRepository personRepository,
      Environment environment
  ) {
    this.personRepository = personRepository;
    this.environment = environment;
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
    logMessages();
    session.setAttribute("loggedInPerson", person);
    return "dashboard.html";
  }

  private void logMessages() {
    log.error("Error message from the Dashboard page");
    log.warn("Warning message from the Dashboard page");
    log.info("Info message from the Dashboard page");
    log.debug("Debug message from the Dashboard page");
    log.trace("Trace message from the Dashboard page");

    log.info(
        "defaultPageSize value with @Value annotation is : " + defaultPageSize
    );
    log.info("successMsg value with @Value annotation is : " + successMsg);

    log.info(
        "defaultPageSize value with Environment is : "
            + environment.getProperty("eazyschool.pageSize")
    );
    log.info(
        "successMsg value with Environment is : "
            + environment.getProperty("eazyschool.contact.successMsg")
    );
    log.info(
        "Java Home environment variable using Environment is : "
            + environment.getProperty("JAVA_HOME")
    );
  }
}
