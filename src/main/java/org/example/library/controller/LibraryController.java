package org.example.library.controller;

import org.example.library.dao.BookDao;
import org.example.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/library")
public class LibraryController {
    @Autowired
    BookDao bookDao;

    @GetMapping("/success")
    public String success() {
        return "success_page";
    }

    @GetMapping("/home")
    public String goHomePage(Model model) {
        model.addAttribute("books", bookDao.findAll());

        return "home_page";
    }

    @GetMapping("/failure")
    private String failure() {
        return "failure_page";
    }
}
