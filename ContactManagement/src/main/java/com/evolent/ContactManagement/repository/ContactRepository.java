package com.evolent.ContactManagement.repository;

import com.evolent.ContactManagement.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends CrudRepository<Contact,Long>{
}
