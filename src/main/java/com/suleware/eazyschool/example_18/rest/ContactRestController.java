package com.suleware.eazyschool.example_18.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suleware.eazyschool.example_18.constants.EazySchoolConstants;
import com.suleware.eazyschool.example_18.model.Contact;
import com.suleware.eazyschool.example_18.model.Response;
import com.suleware.eazyschool.example_18.repository.ContactRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin(origins = "*")
public class ContactRestController {

  private ContactRepository contactRepository;

  public ContactRestController(
      ContactRepository contactRepository
  ) {
    this.contactRepository = contactRepository;
  }

  @GetMapping("/getMessagesByStatus")
  public List<Contact> getmessagesByStatus(
      @RequestParam
      String status
  ) {
    return contactRepository.findByStatus(status);
  }

  @GetMapping("/getAllMsgsByStatus")
  public List<Contact> getAllMsgByStatus(
      @RequestBody
      Contact contact
  ) {
    if (null != contact && null != contact.getStatus()) {
      return contactRepository.findByStatus(contact.getStatus());
    }
    return List.of();
  }

  @PostMapping("/saveMsg")
  public ResponseEntity<Response> saveMsg(
      @RequestHeader
      String invocationFrom,
      @Valid
      @RequestBody
      Contact contact
  ) {
    log.info(String.format("Header invocationFrom = %s", invocationFrom));
    contactRepository.save(contact);
    Response response = new Response();
    response.setStatusCode("200");
    response.setStatusMsg("Message saved successfully");
    return ResponseEntity.status(HttpStatus.CREATED)
        .header("isMsgSaved", "true")
        .body(response);
  }

  @DeleteMapping("/deleteMsg")
  public ResponseEntity<Response> deleteMsg(
      RequestEntity<Contact> requestEntity
  ) {
    HttpHeaders headers = requestEntity.getHeaders();
    headers.forEach((key, value) ->
      {
        log.info(
            String.format(
                "Header '%s' = %s",
                key,
                value.stream().collect(Collectors.joining("|"))
            )
        );
      }
    );
    Contact contact = requestEntity.getBody();
    contactRepository.deleteById(contact.getContactId());
    Response response = new Response();
    response.setStatusCode("200");
    response.setStatusMsg("Message successfully deleted");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/closeMsg")
  public ResponseEntity<Response> closeMsg(
      @RequestBody
      Contact contactReq
  ) {
    Response response = new Response();
    Optional<Contact> contact =
        contactRepository.findById(contactReq.getContactId());
    if (contact.isPresent()) {
      contact.get().setStatus(EazySchoolConstants.CLOSE);
      contactRepository.save(contact.get());
    } else {
      response.setStatusCode("400");
      response.setStatusMsg("Invalid Contact ID received");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    response.setStatusCode("200");
    response.setStatusMsg("Message successfully closed");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
