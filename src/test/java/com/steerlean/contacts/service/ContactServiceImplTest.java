package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.repository.ContactRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContactServiceImplTest {

    @Test
    public void getAllContacts() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.repository = new ContactRepository();
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
    public void getContactById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.repository = new ContactRepository();
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
    public void createOrUpdateContact() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.repository = new ContactRepository();
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
    public void UpdateContact() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.repository = new ContactRepository();
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
    public void deleteContactById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl contactService = new ContactServiceImpl();
        contactService.repository = new ContactRepository();
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