package com.steerlean.contacts.repository;

import com.steerlean.contacts.model.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    UserEntity user1, user2;

    @Before
    public void setUp() {
        user1 = new UserEntity();
        user2 = new UserEntity();

        user1.setUsername("xyz");
        user1.setPassword("1234");
        user1 = repository.save(user1);

        user2.setUsername("jayesh");
        user2.setPassword("456789");
        user2 = repository.save(user2);
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testFindAll() {
        Assert.assertEquals(2, repository.findAll().size());
    }

    @Test
    public void testFindById() {
        Assert.assertEquals("jayesh", repository.findById(user2.getId()).getUsername());
    }

    @Test
    public void testSave() {
        UserEntity user = new UserEntity();
        user.setUsername("jayesh");
        user.setPassword("456789");
        user = repository.save(user);
        Assert.assertEquals(3, repository.findAll().size());

    }

    @Test
    public void testAuthenticate(){
        Assert.assertEquals(true,repository.authenticate("jayesh","456789"));
    }
}