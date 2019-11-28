package com.evolent.ContactManagement.controller;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository repository;

    @GetMapping("/getContacts")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts(){
        List<Contact> result ;
        logger.info("Received request to get all contacts");
        result = (List<Contact>)repository.findAll();
        if (Objects.nonNull(result)){
            logger.info("{} Contacts fetched",result.size());
            return  result;
        }else{
            logger.info("No Contacts found");
            return new ArrayList<Contact>();
        }

    }

    @PostMapping("/addContact")
    @ResponseStatus(HttpStatus.CREATED)
    public void addContact(@RequestBody Contact contact){
        logger.info("Saving contact");
        Contact cont = repository.save(contact);
        logger.info("Save complete . Contact id {}",cont.getId());
    }

    @PutMapping("/updateContact")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@RequestBody Contact contact){
        logger.info("Updating contact");
        Optional<Contact> contact1 = repository.findById(contact.getId());
        if(contact1.isPresent()){
            repository.save(contact);
            logger.info("Updata complete . Contact id {}",contact1.get().getId());
        }

    }

    @DeleteMapping("/deleteContact/{contactid}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void deleteContact(@PathVariable("contactid") Long id){

        Optional<Contact> contact1 = repository.findById(id);
        if(contact1.isPresent()){
            contact1.get().setActive(false);
            repository.save(contact1.get());
            logger.info("Delete complete. Contact id {}",contact1.get().getId());
        }else{
            logger.warn("No record found with Contact id {}",contact1.get().getId());
        }

    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
