package com.evolent.ContactManagement;

import com.evolent.ContactManagement.controller.ContactController;
import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
class ContactManagementApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
    ContactRepository repository;

	@Test
	public void testGetContacts() throws Exception{
	    List<Contact> contacts = new ArrayList();
	    Contact c1 = new Contact();
	    c1.setId(1L);
	    c1.setFirstName("James");
	    c1.setLastName("Bond");
	    c1.setEmail("james.bond@abc.com");
	    c1.setActive(true);

        Contact c2 = new Contact();
        c2.setId(2L);
        c2.setFirstName("Test");
        c2.setLastName("Test2");
        c2.setEmail("test.test@abc.com");
        c2.setActive(true);

        contacts.add(c1);
        contacts.add(c2);

        Mockito.when(repository.findAll()).thenReturn(contacts);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getContacts")).andReturn();

        Assert.assertNotNull(result.getResponse());

	}

    @Test
    public void testAddContacts() throws Exception{
        Contact c1 = new Contact();
        c1.setId(1L);
        c1.setFirstName("James");
        c1.setLastName("Bond");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        Mockito.when(repository.save(Mockito.any())).thenReturn(c1);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addContact")
                .content(new ObjectMapper().writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());


    }

    @Test
    public void testUpdateContacts() throws Exception{
        Contact c1 = new Contact();
        c1.setId(1L);
        c1.setFirstName("James");
        c1.setLastName("Bond");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        Mockito.when(repository.save(Mockito.any())).thenReturn(c1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateContact")
                .content(new ObjectMapper().writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void testDeleteContacts() throws Exception{
        Contact c1 = new Contact();
        c1.setId(1L);
        c1.setFirstName("James");
        c1.setLastName("Bond");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        Mockito.when(repository.save(Mockito.any())).thenReturn(c1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteContact/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMovedPermanently());


    }

}
