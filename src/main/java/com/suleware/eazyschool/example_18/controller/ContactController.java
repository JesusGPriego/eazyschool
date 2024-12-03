package com.suleware.eazyschool.example_18.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suleware.eazyschool.example_18.model.Contact;
import com.suleware.eazyschool.example_18.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContactController {

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

  @GetMapping("/displayMessages/page/{pageNum}")
  public ModelAndView displayMessages(
      Model model,
      @PathVariable(name = "pageNum")
      int pageNum,
      @RequestParam("sortField")
      String sortField,
      @RequestParam("sortDir")
      String sortDir
  ) {
    Page<Contact> msgPage =
        contactService.findMsgsWithOpenStatus(pageNum, sortField, sortDir);
    List<Contact> contactMsgs = msgPage.getContent();
    ModelAndView modelAndView = new ModelAndView("messages.html");
    model.addAttribute("currentPage", pageNum);
    model.addAttribute("totalPages", msgPage.getTotalPages());
    model.addAttribute("totalMsgs", msgPage.getTotalElements());
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model
        .addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    modelAndView.addObject("contactMsgs", contactMsgs);
    return modelAndView;
  }

  @GetMapping(value = "/closeMsg")
  public String closeMsg(
      @RequestParam
      Long id
  ) {
    contactService.updateMsgStatus(id);
    return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
  }
}
