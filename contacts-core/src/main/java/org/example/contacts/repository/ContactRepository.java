package org.example.contacts.repository;

import java.util.List;
import java.util.Optional;

import org.example.contacts.domain.Contact;
import org.springframework.data.repository.Repository;

public interface ContactRepository extends Repository<Contact, Long> {
    Optional<Contact> findById(Long id);
    
    List<Contact> findAll();
    
    Contact save(Contact contact);
    
    void delete(Contact contact);
}
