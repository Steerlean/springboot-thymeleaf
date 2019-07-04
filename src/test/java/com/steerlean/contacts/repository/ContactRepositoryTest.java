package com.steerlean.contacts.repository;

import com.steerlean.contacts.model.ContactEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

    @Autowired private ContactRepository contactRepository;

    private ContactEntity contactEntity1, contactEntity2;

    @Before
    public void setUp(){
        contactEntity1 = new ContactEntity();
        contactEntity2 = new ContactEntity();

        contactEntity1.setFirstName("Om");
        contactEntity1.setLastName("xyz");
        contactEntity1.setEmail("abx@xyz.com");
        contactEntity1 = contactRepository.save(contactEntity1);

        contactEntity2.setFirstName("Sai");
        contactEntity2.setLastName("ABC");
        contactEntity2.setEmail("qpr@xyz.com");
        contactEntity2 = contactRepository.save(contactEntity2);
    }
    @After
    public void tearDown(){
        contactRepository.deleteById(contactEntity1.getId());
        contactRepository.deleteById(contactEntity2.getId());
    }

    @Test
    public void testFindAll() {

        Assert.assertEquals(2, contactRepository
          .findAll()
          .size());
        Assert.assertEquals("abx@xyz.com", contactRepository
          .findAll()
          .get(0)
          .getEmail());
        Assert.assertEquals("Sai", contactRepository
          .findAll()
          .get(1)
          .getFirstName());
        Assert.assertEquals("ABC", contactRepository
          .findAll()
          .get(1)
          .getLastName());
    }

    @Test
    public void testFindById() {
        Optional<ContactEntity> contactEntity = contactRepository.findById(contactEntity1.getId());

        Assert.assertEquals(true, contactEntity.isPresent());
    }

    @Test
    public void testSave() {
        ContactEntity entity = new ContactEntity();
        entity.setEmail("jay@gmail.com");
        entity.setFirstName("jayesh");
        entity.setLastName("ksh");

        ContactEntity actualEntity = contactRepository.save(entity);

        Assert.assertEquals(3, contactRepository.findAll().size());
        Assert.assertEquals(actualEntity.getFirstName(), contactRepository.findAll().get(2).getFirstName());
        contactRepository.deleteById(actualEntity.getId());
    }

    @Test
    public void testDeleteById() {


        contactRepository.deleteById(contactEntity1.getId());

        Assert.assertEquals(1, contactRepository
          .findAll()
          .size());
    }
//
    @Test
    public void testEdit() {
        contactEntity1.setFirstName("Ram");
        contactRepository.edit(contactEntity1);

        Assert.assertEquals("Ram", contactRepository.findAll().get(0).getFirstName());
    }
}