package com.steerlean.contacts;

import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactManagementApp implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("Devesh");
        userEntity1.setPassword("Devesh123");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("Jayesh");
        userEntity2.setPassword("Jayesh123");

        userService.createUser(userEntity1);
        userService.createUser(userEntity2);
    }

}
