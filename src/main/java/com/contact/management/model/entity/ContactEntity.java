package com.contact.management.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Setter
@Getter
@ToString
@Entity
@Table(name = "Contacts")
public class ContactEntity {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
