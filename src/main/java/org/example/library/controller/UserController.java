package org.example.library.controller;

import jakarta.validation.Valid;
import org.example.library.dao.UserDao;
import org.example.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/library_login")
    public String accessLibrary(User user) {
        return "login_page";
    }

    @PostMapping("/user_registered")
    public String checkUserInformation(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "login_page";

        if(userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null)
            return "redirect:/library/failure";
        else
            return "redirect:/library/success";
    }

    @GetMapping("/sign_in")
    public String goSignIn(User user) {
        return "signin_page";
    }

    @PostMapping("/user_signing")
    public String createUser(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "signin_page";

        if(userDao.findByUsername(user.getUsername()) == null) {
            userDao.save(user);

            return "redirect:/library/success";
        } else
            return "signin_page";
    }
}
