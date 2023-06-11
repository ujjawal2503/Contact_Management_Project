package com.contact.management.services;

import com.contact.management.model.Contact;

import java.util.List;

public interface ContactService {
    Contact addContactDetails(Contact contact);

    List<Contact> getByFirstName(String firstName);

    List<Contact> getByLastName(String lastName);

    Contact getContactById(String emailId);
    List<Contact> findAllContact();
    Contact deleteContact(String id);
    Contact updateContact(Contact contact);
}
