package org.example.contacts.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;

@Entity
@lombok.Getter
@lombok.Setter
public class Contact {
    @Id
    @GeneratedValue
    @lombok.Setter(AccessLevel.PRIVATE)
    private Long id;

    private String firstname;
    private String lastname;
    private String title;

    private String phoneNumber;
}
