package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.model.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl {
	
	@Autowired
    ContactRepository repository;
	
    public List<ContactEntity> getAllContacts()
	{
		List<ContactEntity> result = repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<>();
		}
	}
	
    public ContactEntity getContactById(Long id) throws RecordNotFoundException
	{
		Optional<ContactEntity> contact = repository.findById(id);
		
		if(contact.isPresent()) {
			return contact.get();
		} else {
			throw new RecordNotFoundException("No contact record exist for given id");
		}
	}
	
    public ContactEntity createOrUpdateContact(ContactEntity entity)
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<ContactEntity> contact = repository.findById(entity.getId());
			
			if(contact.isPresent())
			{
				ContactEntity newEntity = contact.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
    public void deleteContactById(Long id) throws RecordNotFoundException
	{
		Optional<ContactEntity> contact = repository.findById(id);
		
		if(contact.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No contact record exist for given id");
		}
	} 
}