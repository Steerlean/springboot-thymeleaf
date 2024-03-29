package com.steerlean.contacts.web;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ContactMvcController {
    @Autowired
    ContactService service;

    @RequestMapping(path = "/getAllContactsByUser")
    public String getAllContacts(Model model, @CookieValue("id") String id) {
        List<ContactEntity> list = service.getAllContactsByUser(id);

        model.addAttribute("contacts", list);
        return "list-contacts";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editContactsById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            ContactEntity entity = service.getContactById(id.get());
            model.addAttribute("contact", entity);
            return "edit-contact";
        } else {
            model.addAttribute("contact", new ContactEntity());
        }
        return "add-edit-contact";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteContactsById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteContactById(id);
        return "redirect:/getAllContactsByUser";
    }

    @RequestMapping(path = "/createContact", method = RequestMethod.POST)
    public String createOrUpdateContact(ContactEntity contact, HttpServletRequest request, @CookieValue("id") String id) {
        contact.setUserId(id);
        service.createContact(contact);
        return "redirect:/getAllContactsByUser";
    }

    @RequestMapping(path = "/editContact", method = RequestMethod.POST)
    public String edit(ContactEntity contact, @CookieValue("id") String id) {
        contact.setUserId(id);
        service.editContact(contact);
        return "redirect:/getAllContactsByUser";
    }
}