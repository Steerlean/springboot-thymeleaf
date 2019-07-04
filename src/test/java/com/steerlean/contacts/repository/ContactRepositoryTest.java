package com.steerlean.contacts.repository;

import com.steerlean.contacts.model.ContactEntity;
import org.junit.Assert;
import org.junit.Test;

public class ContactRepositoryTest {

    @Test
    public void findAll() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");
        ContactRepository contactRepository = new ContactRepository();
        contactRepository.save(entity);
        Assert.assertEquals(1, contactRepository
          .findAll()
          .size());
        Assert.assertEquals("jay@gmail.com", contactRepository
          .findAll()
          .get(0)
          .getEmail());
        Assert.assertEquals("jayesh", contactRepository
          .findAll()
          .get(0)
          .getFirstName());
        Assert.assertEquals("ksh", contactRepository
          .findAll()
          .get(0)
          .getLastName());
    }

    @Test
    public void findById() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");
        ContactRepository contactRepository = new ContactRepository();
        entity = contactRepository.save(entity);
        Assert.assertEquals(true, contactRepository
          .findById(entity.getId())
          .isPresent());
    }

    @Test
    public void save() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

        ContactRepository contactRepository = new ContactRepository();
        ContactEntity actualEntity = contactRepository.save(entity);
        Assert.assertEquals(actualEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(actualEntity.getFirstName(), entity.getFirstName());
        Assert.assertEquals(actualEntity.getLastName(), entity.getLastName());
    }

    @Test
    public void deleteById() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

        ContactRepository contactRepository = new ContactRepository();
        ContactEntity actualEntity = contactRepository.save(entity);

        Assert.assertEquals(1, contactRepository
          .findAll()
          .size());

        contactRepository.deleteById(actualEntity.getId());

        Assert.assertEquals(0, contactRepository
          .findAll()
          .size());
    }
}