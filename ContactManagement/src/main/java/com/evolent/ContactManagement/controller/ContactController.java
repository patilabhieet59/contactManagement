package com.evolent.ContactManagement.controller;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

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
    public void addContact(@RequestBody Contact contact){
        System.out.println("saving contact "+ contact.getFirstName());
        Contact cont = repository.save(contact);
    }

    @PutMapping("/updateContact")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@RequestBody Contact contact){
        Optional<Contact> contact1 = repository.findById(contact.getId());
        if(contact1.isPresent()){
            repository.save(contact);
        }

    }

    @DeleteMapping("/deleteContact/{contactid}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void deleteContact(@PathVariable("contactid") Long id){
        System.out.println("----------------  "+id);
        Optional<Contact> contact1 = repository.findById(id);
        if(contact1.isPresent()){
            contact1.get().setActive(false);
            repository.save(contact1.get());
        }

    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
