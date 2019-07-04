package com.steerlean.contacts.web;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ContactMvcController {
    @Autowired ContactService service;

    @RequestMapping
    public String getAllContacts(Model model) {
        List<ContactEntity> list = service.getAllContacts();

        model.addAttribute("contacts", list);
        return "list-contacts";
    }

    @RequestMapping(path = { "/edit", "/edit/{id}" })
    public String editContactsById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            ContactEntity entity = service.getContactById(id.get());
            model.addAttribute("contact", entity);
        } else {
            model.addAttribute("contact", new ContactEntity());
        }
        return "add-edit-contact";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteContactsById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteContactById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createContact", method = RequestMethod.POST)
    public String createOrUpdateContact(ContactEntity contact) {
        service.createOrUpdateContact(contact);
        return "redirect:/";
    }
}
