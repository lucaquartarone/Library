package org.example.library.controller;

import org.example.library.dao.BookDao;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/json")
public class JsonController {
    @Autowired
    BookDao bookDao;

    @RequestMapping("/all_books")
    public List<Book> booksInJson() {
        return (List<Book>) bookDao.findAll();
    }

    @PostMapping("/book_by_title")
    public List<Book> findByTitleJson(BookRepository bookRepository) {
        //System.out.println("JSON Controller " + bookRepository.ge);

        System.out.println(bookRepository.getBooksList());
        return bookRepository.getBooksList();
    }
}
