package com.steerlean.contacts.web;

import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    ContactService service;

    private Random random = new Random();

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
    public String login(@ModelAttribute(name = "loginForm") UserEntity userEntity, Model model, HttpSession httpSession,
                        HttpServletResponse response) {
        String username = userEntity.getUsername();
        String password = userEntity.getPassword();
        if ("admin".equals(username) && "admin".equals(password)) {
            httpSession.setAttribute("username", userEntity.getUsername());
            Long id = random.nextLong();
            System.out.println("\n\n\n\n"+ id+"\n\n\n\n");
            response.addCookie(new Cookie("id", id + ""));
            userEntity.setId(id);
            List<ContactEntity> list = service.getAllContacts();
            model.addAttribute("contacts", list);
            return "list-contacts";
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

}

