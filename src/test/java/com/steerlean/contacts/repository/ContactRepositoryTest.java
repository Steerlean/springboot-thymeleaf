package com.steerlean.contacts.repository;

import com.steerlean.contacts.model.ContactEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

    @Autowired private ContactRepository contactRepository;

    @Test
    public void testFindAll() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

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
    public void testFindById() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

        entity = contactRepository.save(entity);

        Assert.assertEquals(true, contactRepository
          .findById(entity.getId())
          .isPresent());
    }

    @Test
    public void testSave() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

        ContactEntity actualEntity = contactRepository.save(entity);

        Assert.assertEquals(actualEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(actualEntity.getFirstName(), entity.getFirstName());
        Assert.assertEquals(actualEntity.getLastName(), entity.getLastName());
    }

    @Test
    public void testDeleteById() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

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