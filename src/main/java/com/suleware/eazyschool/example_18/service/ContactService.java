package com.suleware.eazyschool.example_18.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.suleware.eazyschool.example_18.constants.EazySchoolConstants;
import com.suleware.eazyschool.example_18.model.Contact;
import com.suleware.eazyschool.example_18.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {
  private ContactRepository contactRepository;

  public ContactService(
      ContactRepository contactRepository
  ) {
    this.contactRepository = contactRepository;
  }

  /**
   * Save Contact Details into DB
   * 
   * @param contact
   * 
   * @return boolean
   */
  public boolean saveMessageDetails(
      Contact contact
  ) {
    boolean isSaved = false;
    contact.setStatus(EazySchoolConstants.OPEN);
    Contact savedContact = contactRepository.save(contact);
    if (null != savedContact && savedContact.getContactId() > 0) {
      isSaved = true;
    }
    return isSaved;
  }

  public List<Contact> findMsgsWithOpenStatus() {
    return contactRepository.findByStatus(EazySchoolConstants.OPEN);
  }

  public boolean updateMsgStatus(
      Long contactId
  ) {
    boolean isUpdated = false;
    Optional<Contact> contact = contactRepository.findById(contactId);

    contact
        .ifPresent(contact1 -> contact1.setStatus(EazySchoolConstants.CLOSE));
        
    Contact updatedContact = contactRepository.save(contact.get());
    if (null != updatedContact && updatedContact.getUpdatedBy() != null) {
      isUpdated = true;
    }
    return isUpdated;
  }
}
