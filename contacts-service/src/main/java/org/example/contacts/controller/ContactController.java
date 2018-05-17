package org.example.contacts.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.function.Function;

import javax.validation.Valid;

import org.example.contacts.domain.Contact;
import org.example.contacts.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactRepository repository;
    
    @Autowired
    private ContactResourceAssembler assembler;
    
    @Autowired
    private ModelMapper mapper;
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ContactResource request) {
        Contact contact = new Contact();
        mapper.map(request, contact);
        
        contact = repository.save(contact);
        
        ContactResource resource = assembler.toResource(contact);
        return ResponseEntity.created(URI.create(resource.getId().getHref())).body(resource);
    }
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        Resources<ContactResource> resources = new Resources<>(assembler.toResources(repository.findAll()));
        
        resources.add(linkTo(methodOn(ContactController.class).getAll()).withSelfRel());
        
        return ResponseEntity.ok(resources);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return withContact(id, contact -> {
           return ResponseEntity.ok(assembler.toResource(contact)); 
        });
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ContactResource request) {
        return withContact(id, contact -> {
           mapper.map(request, contact);
           Contact savedContact = repository.save(contact);
           return ResponseEntity.ok(assembler.toResource(savedContact));
        });
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return withContact(id, contact -> {
           repository.delete(contact);
           
           return ResponseEntity.noContent().build();
        });
    }
    
    private ResponseEntity<?> withContact(Long id, Function<Contact, ResponseEntity<?>> f) {
        return repository.findById(id)
                .map(f)
                .orElse(ResponseEntity.notFound().build());
    }
}
