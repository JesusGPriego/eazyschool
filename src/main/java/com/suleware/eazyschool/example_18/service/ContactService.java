package com.suleware.eazyschool.example_18.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suleware.eazyschool.example_18.model.Contact;

@Service
public class ContactService {

    private Logger log = LoggerFactory.getLogger(ContactService.class);

    /**
     * Save contact into DB
     * 
     * @param contact
     * @return boolean
     */
    public boolean saveContact(Contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
}
