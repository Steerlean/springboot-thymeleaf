package com.steerlean.contacts.service;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<ContactEntity> getAllContacts() {
        List<ContactEntity> result = repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public List<ContactEntity> getAllContactsByUser(String id) {
        List<ContactEntity> result = repository.findAll();
        List<ContactEntity> contactByUser = new ArrayList<>();
        if (result.size() > 0) {
            for (ContactEntity contact : result) {
                if (contact.getUserId().equals(id)) {
                    contactByUser.add(contact);
                }
            }
        }
        return contactByUser;
    }

    public ContactEntity getContactById(Long id) throws RecordNotFoundException {
        Optional<ContactEntity> contact = repository.findById(id);

        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new RecordNotFoundException("No contact record exist for given id");
        }
    }

    public ContactEntity createContact(ContactEntity entity) {
        entity = repository.save(entity);
        return entity;
    }

    public void deleteContactById(Long id) throws RecordNotFoundException {
        Optional<ContactEntity> contact = repository.findById(id);

        if (contact.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No contact record exist for given id");
        }
    }

    public void editContact(ContactEntity entity) {
        repository.edit(entity);
    }
}