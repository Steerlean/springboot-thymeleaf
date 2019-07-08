package com.steerlean.contacts.repository;

import com.steerlean.contacts.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UserRepository {
    Random random = new Random();
    private List<UserEntity> users;

    public boolean authenticate(String uname, String pass) {
        for (UserEntity user : users) {
            if (user.getUsername().equals(uname) && user.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public List<UserEntity> findAll() {
        return users;
    }

    public UserEntity findById(Long id) {
        for (UserEntity entity : users) {
            if (entity
                    .getId()
                    .equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public UserEntity save(UserEntity entity) {
        entity.setId(random.nextLong());
        users.add(entity);
        return entity;
    }

    public void deleteAll() {
        users = new ArrayList<>();
    }
}
