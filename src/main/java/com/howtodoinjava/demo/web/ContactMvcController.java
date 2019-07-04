package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import com.howtodoinjava.demo.model.ContactEntity;
import com.howtodoinjava.demo.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.demo.exception.RecordNotFoundException;

@Controller
@RequestMapping("/")
public class ContactMvcController {
    @Autowired
    ContactServiceImpl service;

    @RequestMapping
    public String getAllContacts(Model model) {
        List<ContactEntity> list = service.getAllContacts();

        model.addAttribute("employees", list);
        return "list-employees";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editContactsById(Model model, @PathVariable("id") Optional<Long> id)
            throws RecordNotFoundException {
        if (id.isPresent()) {
            ContactEntity entity = service.getContactById(id.get());
            model.addAttribute("employee", entity);
        } else {
            model.addAttribute("employee", new ContactEntity());
        }
        return "add-edit-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteContactsById(Model model, @PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteContactById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateContact(ContactEntity employee) {
        service.createOrUpdateContact(employee);
        return "redirect:/";
    }
}