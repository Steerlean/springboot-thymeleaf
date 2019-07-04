package com.howtodoinjava.demo.repository;

import com.howtodoinjava.demo.model.ContactEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class ContactRepository {

    Random random = new Random();
    private List<ContactEntity> contacts;

    public ContactRepository() {
        this.contacts = new ArrayList<>();
    }

    public List<ContactEntity> findAll() {
        return contacts;
    }

    public Optional<ContactEntity> findById(Long id) {
        for (ContactEntity entity : contacts) {
            if (entity.getId().equals(id)) {
                return Optional.of(entity);
            }
        }
        return null;
    }

    public ContactEntity save(ContactEntity entity) {
        entity.setId(random.nextLong());
        contacts.add(entity);
        return entity;
    }

    public void deleteById(Long id) {
        int i = -1;
        for (ContactEntity entity : contacts) {
            i++;
            if (entity.getId().equals(id)) {
                break;
            }
        }
        if (i >= 0) {
            contacts.remove(i);
        }
    }
}
