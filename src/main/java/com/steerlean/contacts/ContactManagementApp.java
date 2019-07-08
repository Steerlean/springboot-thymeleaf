package com.steerlean.contacts;

import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.repository.UserRepository;
import com.steerlean.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactManagementApp {

    public static void main(String[] args) {
        SpringApplication.run(ContactManagementApp.class, args);
    }

}
