package com.steerlean.contacts.web;

import com.steerlean.contacts.model.ContactEntity;
import com.steerlean.contacts.model.UserEntity;
import com.steerlean.contacts.service.ContactService;
import com.steerlean.contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    ContactService contactService;

    @Autowired
    UserService userService;

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

        if (userService.authenticate(username, password)) {
            httpSession.setAttribute("username", userEntity.getUsername());
            Long id = random.nextLong();
//            response.addCookie(new Cookie("id", id + ""));
            response.addCookie(new Cookie("id", userEntity.getUsername()));
            userEntity.setId(id);

            List<ContactEntity> list = contactService.getAllContactsByUser(userEntity.getUsername());
            model.addAttribute("contacts", list);
            return "list-contacts";
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

}