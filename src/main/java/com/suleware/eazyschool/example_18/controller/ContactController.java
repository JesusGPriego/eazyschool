package com.suleware.eazyschool.example_18.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suleware.eazyschool.example_18.model.Contact;
import com.suleware.eazyschool.example_18.service.ContactService;

@Controller
public class ContactController {
    private Logger log = LoggerFactory.getLogger(ContactController.class);

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/contact")
    public String getContact() {
        return "contact.html";
    }

    // @PostMapping(value = "/saveMsg")
    // public ModelAndView saveMsg(
    // @RequestParam("name") String name,
    // @RequestParam("mobileNum") String mobileNum,
    // @RequestParam("email") String email,
    // @RequestParam("subject") String subject,
    // @RequestParam("message") String message) {
    // System.out.println(name + " " + mobileNum + " " + email + " " + subject + " "
    // + message);
    // return new ModelAndView("redirect:/contact");
    // }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveContact(
            Contact contact) {
        boolean isSaved = contactService.saveContact(contact);
        return new ModelAndView("redirect:/contact");
    }
}
