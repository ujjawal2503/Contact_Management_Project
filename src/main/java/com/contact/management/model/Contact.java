package com.contact.management.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Contact {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
