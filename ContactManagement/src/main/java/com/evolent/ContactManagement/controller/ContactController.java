package com.evolent.ContactManagement.controller;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @GetMapping("/getContacts")
    public List<Contact> getContacts(){
        return  (List<Contact>)repository.findAll();
    }

    @PostMapping("/addContact")
    public void addContact(Contact contact){
        repository.save(contact);
    }

    @PutMapping("/updateContact")
    public void updateContact(){

    }

    @DeleteMapping("/deleteContact")
    public void deleteContact(){

    }
}
