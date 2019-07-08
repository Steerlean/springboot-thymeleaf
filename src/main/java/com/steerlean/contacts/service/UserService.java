package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    public boolean getAuthenticate(String uname, String pass) {
        return repository.authenticate(uname, pass);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> userList = repository.findAll();
        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity getUserById(Long id) throws RecordNotFoundException {
        UserEntity user = repository.findById(id);

        if (user != null) {
            return user;
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }
    }

    public UserEntity createUser(UserEntity entity) {
        entity = repository.save(entity);
        return entity;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
