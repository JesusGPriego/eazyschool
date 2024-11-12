package com.suleware.eazyschool.example_18.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suleware.eazyschool.example_18.model.Contact;
import com.suleware.eazyschool.example_18.service.ContactService;

import jakarta.validation.Valid;

@Controller
public class ContactController {
  private Logger log = LoggerFactory.getLogger(ContactController.class);

  private ContactService contactService;

  public ContactController(
      ContactService contactService
  ) {
    this.contactService = contactService;
  }

  @GetMapping("/contact")
  public String displayContactPage(
      Model model
  ) {
    model.addAttribute("contact", new Contact());
    return "contact.html";
  }

  @PostMapping(value = "/saveMsg")
  public String saveMessage(
      @Valid
      @ModelAttribute("contact")
      Contact contact,
      Errors errors
  ) {
    if (errors.hasErrors()) {
      log.error(
          String.format(
              "Contact form validation failed due to: %s",
              errors.toString()
          )
      );
      return "contact.html";
    }
    contactService.saveMessageDetails(contact);
    return "redirect:/contact";
  }

  @GetMapping("/displayMessages")
  public ModelAndView displayMessages(
      Model model
  ) {
    List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
    ModelAndView modelAndView = new ModelAndView("messages.html");
    modelAndView.addObject("contactMsgs", contactMsgs);
    return modelAndView;
  }

  @GetMapping(value = "/closeMsg")
  public String closeMsg(
      @RequestParam
      Long id,
      Authentication authentication
  ) {
    contactService.updateMsgStatus(id, authentication.getName());
    return "redirect:/displayMessages";
  }
}
