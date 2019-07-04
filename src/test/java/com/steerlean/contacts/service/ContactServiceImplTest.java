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
    public void getAllEmployees() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl employeeService = new ContactServiceImpl();
        employeeService.repository = new ContactRepository();
        employeeService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(1, employeeService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntity.getEmail(), employeeService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntity.getFirstName(), employeeService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntity.getLastName(), employeeService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void getEmployeeById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl employeeService = new ContactServiceImpl();
        employeeService.repository = new ContactRepository();
        contactEntity = employeeService.createOrUpdateContact(contactEntity);
        contactEntity = employeeService.getContactById(contactEntity.getId());
        Assert.assertEquals(1, employeeService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntity.getEmail(), employeeService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntity.getFirstName(), employeeService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntity.getLastName(), employeeService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void createOrUpdateEmployee() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl employeeService = new ContactServiceImpl();
        employeeService.repository = new ContactRepository();
        contactEntityActual = employeeService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(1, employeeService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntityActual.getEmail(), employeeService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntityActual.getFirstName(), employeeService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntityActual.getLastName(), employeeService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void UpdateEmployee() {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl employeeService = new ContactServiceImpl();
        employeeService.repository = new ContactRepository();
        contactEntity = employeeService.createOrUpdateContact(contactEntity);
        contactEntity.setEmail("abx@xyz.com");
        contactEntityActual = employeeService.createOrUpdateContact(contactEntity);

        Assert.assertEquals(2, employeeService
          .getAllContacts()
          .size());
        Assert.assertEquals(contactEntityActual.getEmail(), employeeService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntityActual.getFirstName(), employeeService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntityActual.getLastName(), employeeService
          .getAllContacts()
          .get(0)
          .getLastName());
    }

    @Test
    public void deleteEmployeeById() throws RecordNotFoundException {
        ContactEntity contactEntity = new ContactEntity();
        ContactEntity contactEntityActual;
        contactEntity.setFirstName("Om");
        contactEntity.setLastName("xyz");
        contactEntity.setEmail("abx@xyz.com");

        ContactServiceImpl employeeService = new ContactServiceImpl();
        employeeService.repository = new ContactRepository();
        contactEntity = employeeService.createOrUpdateContact(contactEntity);
        contactEntityActual = employeeService.getContactById(contactEntity.getId());

        Assert.assertEquals(contactEntityActual.getEmail(), employeeService
          .getAllContacts()
          .get(0)
          .getEmail());
        Assert.assertEquals(contactEntityActual.getFirstName(), employeeService
          .getAllContacts()
          .get(0)
          .getFirstName());
        Assert.assertEquals(contactEntityActual.getLastName(), employeeService
          .getAllContacts()
          .get(0)
          .getLastName());
    }
}