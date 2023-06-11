package com.contact.management.services.impl;

import com.contact.management.dao.ContactRepository;
import com.contact.management.model.Contact;
import com.contact.management.model.entity.ContactEntity;
import com.contact.management.services.ContactService;
import com.contact.management.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Contact addContactDetails(Contact contact) {
        ContactEntity contactEntity = modelMapper.map(contact,ContactEntity.class);
        contactRepository.save(contactEntity);
        return contact;
    }

    @Override
    public List<Contact> getByFirstName(String firstName) {
        List<ContactEntity> contactEntityList = contactRepository.findByFirstName(firstName);
        List<Contact> contactList = new ArrayList<>();
        for (ContactEntity contact: contactEntityList
             ) {
            contactList.add(modelMapper.map(contact,Contact.class));
        }
        return contactList;
    }

    @Override
    public List<Contact> getByLastName(String lastName) {
        List<ContactEntity> contactEntityList = contactRepository.findByLastName(lastName);
        List<Contact> contactList = new ArrayList<>();
        for (ContactEntity contact: contactEntityList
        ) {
            contactList.add(modelMapper.map(contact,Contact.class));
        }
        return contactList;
    }

    @Override
    public Contact getContactById(String emailId) {
        ContactEntity contactEntity = contactRepository.findByEmail(emailId).orElseThrow(()->new ResourceNotFoundException("Contact doesn't exist with "+emailId));
        Contact contact = modelMapper.map(contactEntity,Contact.class);
        return contact;
    }

    @Override
    public List<Contact> findAllContact() {
        List<ContactEntity> contactEntityList = contactRepository.findAll();
        List<Contact> contactList = new ArrayList<>();
        for (ContactEntity contact: contactEntityList
        ) {
            contactList.add(modelMapper.map(contact,Contact.class));
        }
        return contactList;
    }

    @Override
    public Contact deleteContact(String id) {
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact doesn't exist with "+id));
        contactRepository.delete(contactEntity);
        return modelMapper.map(contactEntity,Contact.class);
    }

    @Override
    public Contact updateContact(Contact contact) {
      Boolean contactExist = contactRepository.existsById(contact.getEmail());
      if(contactExist){
          ContactEntity contactEntity =modelMapper.map(contact,ContactEntity.class);
          contactRepository.save(contactEntity);
          return contact;
      }else {
          throw  new ResourceNotFoundException("Contact doesn't exist with "+contact.getEmail());
      }
    }
}
