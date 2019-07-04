package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    private ContactEntity contactEntity1, contactEntity2;

    @Before
    public void setUp() {
        contactEntity1 = new ContactEntity();
        contactEntity2 = new ContactEntity();

        contactEntity1.setFirstName("Om");
        contactEntity1.setLastName("xyz");
        contactEntity1.setEmail("abx@xyz.com");
        contactEntity1 = contactService.createContact(contactEntity1);

        contactEntity2.setFirstName("Sai");
        contactEntity2.setLastName("ABC");
        contactEntity2.setEmail("qpr@xyz.com");
        contactEntity2 = contactService.createContact(contactEntity2);

    }

    @After
    public void tearDown() throws RecordNotFoundException {
        contactService.deleteContactById(contactEntity1.getId());
        contactService.deleteContactById(contactEntity2.getId());
    }

    @Test
    public void testGetAllContacts() {

        Assert.assertEquals(2, contactService
                .getAllContacts()
                .size());
        Assert.assertEquals("abx@xyz.com", contactService
                .getAllContacts()
                .get(0)
                .getEmail());
        Assert.assertEquals("Om", contactService
                .getAllContacts()
                .get(0)
                .getFirstName());
        Assert.assertEquals("xyz", contactService
                .getAllContacts()
                .get(0)
                .getLastName());

        Assert.assertEquals("qpr@xyz.com", contactService
                .getAllContacts()
                .get(1)
                .getEmail());
        Assert.assertEquals("Sai", contactService
                .getAllContacts()
                .get(1)
                .getFirstName());
        Assert.assertEquals("ABC", contactService
                .getAllContacts()
                .get(1)
                .getLastName());
    }

    @Test
    public void testGetContactById() throws RecordNotFoundException {
        ContactEntity contactEntity = contactService.getContactById(contactEntity1.getId());

        Assert.assertEquals(contactEntity1.getEmail(), contactEntity.getEmail());
        Assert.assertEquals(contactEntity1.getFirstName(), contactEntity.getFirstName());
        Assert.assertEquals(contactEntity1.getLastName(), contactEntity.getLastName());
    }

    @Test
    public void testCreateContact() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactEntityActual = contactService.createContact(contactEntity);

        Assert.assertEquals(3, contactService
                .getAllContacts()
                .size());
        contactService.deleteContactById(contactEntityActual.getId());
    }

    @Test
    public void testEditContact() {
        contactEntity1.setEmail("EFG@xyz.com");
        contactService.editContact(contactEntity1);

        Assert.assertEquals("EFG@xyz.com", contactService
                .getAllContacts()
                .get(0)
                .getEmail());
        Assert.assertEquals("Om", contactService
                .getAllContacts()
                .get(0)
                .getFirstName());
        Assert.assertEquals("xyz", contactService
                .getAllContacts()
                .get(0)
                .getLastName());
    }

    @Test
    public void testDeleteContactById() throws RecordNotFoundException {
        ContactEntity contactEntityActual = contactService.getContactById(contactEntity1.getId());

        Assert.assertEquals(contactEntityActual.getEmail(), contactService
                .getAllContacts()
                .get(0)
                .getEmail());
        Assert.assertEquals(contactEntityActual.getFirstName(), contactService
                .getAllContacts()
                .get(0)
                .getFirstName());
        Assert.assertEquals(contactEntityActual.getLastName(), contactService
                .getAllContacts()
                .get(0)
                .getLastName());
    }
}