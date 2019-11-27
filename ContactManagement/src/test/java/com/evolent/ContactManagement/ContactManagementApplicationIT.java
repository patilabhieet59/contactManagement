package com.evolent.ContactManagement;

import com.evolent.ContactManagement.model.Contact;
import com.evolent.ContactManagement.repository.ContactRepository;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
        c1.setFirstName("James");
        c1.setLastName("Bond");
        c1.setEmail("james.bond@abc.com");
        c1.setActive(true);

        mvc.perform(MockMvcRequestBuilders.post("/addContact",c1)).andExpect(MockMvcResultMatchers.status().isCreated());
        mvc.perform(MockMvcRequestBuilders.get("/getContacts")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("James") ));

    }
}
