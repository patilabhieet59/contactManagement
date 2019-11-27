package com.evolent.ContactManagement.controller;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @GetMapping("/getContacts")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts(){
        return  (List<Contact>)repository.findAll();
    }

    @PostMapping("/addContact")
    @ResponseStatus(HttpStatus.CREATED)
    public void addContact(Contact contact){
        Contact cont = repository.save(contact);
    }

    @PutMapping("/updateContact")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(Contact contact){
        repository.save(contact);
    }

    @DeleteMapping("/deleteContact")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void deleteContact(Long id){
        repository.deleteById(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
