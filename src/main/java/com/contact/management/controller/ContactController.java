package com.contact.management.controller;

import com.contact.management.model.Contact;
import com.contact.management.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    //Adding the contact details
    @PostMapping(value = "/add")
    ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        Contact contactCreated =  contactService.addContactDetails(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactCreated);
    }
    //retrieving the details based on firstName
    @GetMapping(value = "/firstName/{firstName}")
    ResponseEntity<List<Contact>> getContactByFirstName(@PathVariable String firstName){
      List<Contact> contact = contactService.getByFirstName(firstName);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
    //retrieving the details based on lastName
    @GetMapping(value = "/lastName/{lastName}")
    ResponseEntity<List<Contact>> getContactByLastName(@PathVariable String lastName){
        List<Contact> contact = contactService.getByLastName(lastName);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
    //retrieving the details based on emailId
    @GetMapping(value = "/emailId/{emailId}")
    ResponseEntity<Contact> getContactById(@PathVariable String emailId){
        Contact contact = contactService.getContactById(emailId);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
    //Fetching all the contact details
    @GetMapping
    ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> contact = contactService.findAllContact();
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }
    //Delete the Contact Details with the help of ID
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Contact> deleteContact(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.deleteContact(id));
    }
    //Update the details of the User
    @PutMapping(value = "/update")
    ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
       return ResponseEntity.status(HttpStatus.OK).body(contactService.updateContact(contact));
    }
}
