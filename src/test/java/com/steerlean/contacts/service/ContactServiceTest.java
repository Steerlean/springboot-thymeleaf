package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

    @Autowired private ContactService contactService;

    @Test
    public void testGetAllContacts() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(1, contactService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntity.getEmail(), contactService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntity.getFirstName(), contactService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntity.getLastName(), contactService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void testGetContactById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactEntity = contactService.createOrUpdateContact(contactEntity);
        contactEntity = contactService.getContactById(contactEntity.getId());
        Assert.assertEquals(1, contactService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntity.getEmail(), contactService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntity.getFirstName(), contactService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntity.getLastName(), contactService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void testCreateOrUpdateContact() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactEntityActual = contactService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(1, contactService
          .getAllContacts()
          .size());
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

    @Test
    public void testUpdateContact() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactEntity = contactService.createOrUpdateContact(contactEntity);
        contactEntity.setEmail("abx@xyz.com");
        contactEntityActual = contactService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(2, contactService
          .getAllContacts()
          .size());
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

    @Test
    public void testDeleteContactById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        contactEntity = contactService.createOrUpdateContact(contactEntity);
        contactEntityActual = contactService.getContactById(contactEntity.getId());

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