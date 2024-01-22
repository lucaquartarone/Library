package org.example.library.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.library.dao.UserDao;
import org.example.library.model.User;
import org.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String checkUserInformation(@Valid User user, BindingResult bindingResult, HttpSession httpSession) {
        if(bindingResult.hasErrors())
            return "login_page";

        User user1 = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if(user1 == null)
            return "redirect:/library/failure";
        else {
            httpSession.setAttribute("user", user1);

            return "redirect:/library/success";
        }
    }

    @GetMapping("/sign_in")
    public String goSignIn(User user) {
        return "signin_page";
    }

    @PostMapping("/user_signing")
    public String createUser(@Valid User user, BindingResult bindingResult, HttpSession httpSession) {
        if(bindingResult.hasErrors())
            return "signin_page";

        if(userDao.findByUsername(user.getUsername()) == null) {
            userDao.save(user);
            httpSession.setAttribute("user", user);

            return "redirect:/library/success";
        } else
            return "signin_page";
    }

    @RequestMapping("/detail")
    public ModelAndView userDetail(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("user_detail");

        User user = (User) httpSession.getAttribute("user");

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
        httpSession.setAttribute("user", null);

        return "redirect:/user/library_login";
    }
}
