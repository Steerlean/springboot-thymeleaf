package com.steerlean.contacts.web;

import com.steerlean.contacts.exception.RecordNotFoundException;
import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ContactMvcController {
    @Autowired
    ContactService service;

    @RequestMapping(path = "/logout")
    public String logOut(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("username");
        httpSession.invalidate();
        return "login";
    }

    @RequestMapping(path = "/")
    public String getLoginForm() {
        return "login";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "loginForm") UserEntity userEntity, Model model, HttpSession httpSession) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        if ("admin".equals(username) && "admin".equals(password)) {
            httpSession.setAttribute("username", userEntity.getUsername());

            List<ContactEntity> list = service.getAllContacts();

            model.addAttribute("contacts", list);
            return "list-contacts";
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @RequestMapping(path = "/getAllContacts")
    public String getAllContacts(Model model) {
        List<ContactEntity> list = service.getAllContacts();

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
        return "redirect:/getAllContacts";
    }

    @RequestMapping(path = "/createContact", method = RequestMethod.POST)
    public String createOrUpdateContact(ContactEntity contact) {
        service.createContact(contact);
        return "redirect:/getAllContacts";
    }

    @RequestMapping(path = "/editContact", method = RequestMethod.POST)
    public String edtit(ContactEntity contact) {
        service.editContact(contact);
        return "redirect:/getAllContacts";
    }
}
