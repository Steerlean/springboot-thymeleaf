package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
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
public class UserServiceTest {

    @Autowired
    UserService userService;

    private UserEntity user1, user2;

    @Before
    public void setUp() {
        user1 = new UserEntity();
        user2 = new UserEntity();

        user1.setUsername("abc");
        user1.setPassword("123456");

        user1 = userService.createUser(user1);

        user2.setUsername("xyz");
        user2.setPassword("123456");

        user2 = userService.createUser(user2);
    }

    @After
    public void tearDown() {
        userService.deleteAll();
    }

    @Test
    public void testGetAllUsers() {
        Assert.assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void testGetUserById() throws RecordNotFoundException {
        Assert.assertEquals("xyz", userService.getUserById(user2.getId()).getUsername());
    }

    @Test
    public void testCreateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("xyz");
        userEntity.setPassword("123456");

        userEntity = userService.createUser(userEntity);
        Assert.assertEquals(3,userService.getAllUsers().size());
    }

    @Test
    public void testGetAuthenticate(){
        Assert.assertEquals(true,userService.authenticate("xyz","123456"));
    }
}