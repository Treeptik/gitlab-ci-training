package org.example.contacts.controller;

import org.example.contacts.domain.Contact;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ContactResourceAssembler extends ResourceAssemblerSupport<Contact, ContactResource> {
    @Autowired
    private ModelMapper mapper;
    
    public ContactResourceAssembler() {
        super(ContactController.class, ContactResource.class);
    }
    
    @Override
    public ContactResource toResource(Contact entity) {
        ContactResource resource = createResourceWithId(entity.getId(), entity);
        mapper.map(entity, resource);
        
        return resource;
    }

}
