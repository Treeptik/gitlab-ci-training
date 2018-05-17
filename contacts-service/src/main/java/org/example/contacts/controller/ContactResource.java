package org.example.contacts.controller;

import org.springframework.hateoas.ResourceSupport;

@lombok.Getter @lombok.Setter
@lombok.ToString
public class ContactResource extends ResourceSupport {
    private String firstname;
    private String lastname;
    private String title;
    
    private String phoneNumber;
}
