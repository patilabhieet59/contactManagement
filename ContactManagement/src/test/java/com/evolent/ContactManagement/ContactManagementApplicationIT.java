package com.evolent.ContactManagement;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sun.nio.cs.Surrogate;

@RunWith(SpringRunner.class)
@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
        classes = ContactManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ContactManagementApplicationIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ContactRepository repository;

    @Test
    public void testGetContacts() throws Exception {

        Contact c1 = new Contact();
//        c1.setId(1L);
        c1.setFirstName("Lokesh");
        c1.setLastName("Gupta");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/addContact")
                .content(new ObjectMapper().writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.get("/getContacts")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Lokesh") ))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("Gupta") ));

        c1.setId(1L);
        c1.setFirstName("Bruce");
        mvc.perform(MockMvcRequestBuilders
                .put("/updateContact")
                .content(new ObjectMapper().writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());


        mvc.perform(MockMvcRequestBuilders.get("/getContacts")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Bruce") ));
    }

    @Test
    public void testDeleteContacts() throws Exception {

        Contact c1 = new Contact();
//        c1.setId(1L);
        c1.setFirstName("Lokesh");
        c1.setLastName("Gupta");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/addContact")
                .content(new ObjectMapper().writeValueAsString(c1))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(MockMvcRequestBuilders.get("/getContacts")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Lokesh")));

        mvc.perform(MockMvcRequestBuilders.delete("/deleteContact/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isMovedPermanently());

        mvc.perform(MockMvcRequestBuilders.get("/getContacts")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(false)));

    }

}
