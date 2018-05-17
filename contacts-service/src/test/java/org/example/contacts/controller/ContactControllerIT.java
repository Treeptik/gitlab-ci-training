package org.example.contacts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerIT {
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCreate() throws Exception {
        ContactResource request = new ContactResource();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setTitle("Mr.");
        request.setPhoneNumber("555-123-4567");
        
        ResultActions result = mvc.perform(post("/contacts")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_UTF8));
        
        result.andExpect(status().isCreated());
    }
}
