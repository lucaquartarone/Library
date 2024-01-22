package org.example.library.controller;

import org.example.library.dao.BookDao;
import org.example.library.model.Book;
import org.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookDao bookDao;

    @GetMapping("/new_book")
    public String addBook(Book book) {
        return "new_book_page";
    }

    @PostMapping("/book_added")
    public String checkBookInformation(Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "new_book_page";

        if(bookDao.findByAuthorAndTitle(book.getAuthor(), book.getTitle()) == null) {
            bookDao.save(book);

            return "redirect:/library/success";
        } else
            return "redirect:/books/book_failure";
    }

    @GetMapping("/find_book")
    public String findBookByTitle(Book book) {
        return "book_title_search";
    }

    @PostMapping("/book_found")
    public String bookFound(Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "book_title_search";

        if(!bookDao.findAllByTitle(book.getTitle()).isEmpty())
            return "redirect:/books/book_result?title=" + book.getTitle();
        else
            return "search_failed";
    }

    @GetMapping("/book_failure")
    public String bookInsertError() {
        return "add_book_fail";
    }

    @GetMapping("/book_result")
    public String bookResult(@RequestParam String title, Model model) {
        List<Book> books = bookDao.findAllByTitle(title);
        BookRepository bookRepository = new BookRepository();

        bookRepository.setBooksList(books);

        System.out.println("Book controller " + books.get(0).getTitle());

        model.addAttribute("bookRepository", bookRepository);
        model.addAttribute("books", books);

        return "book_page";
    }

    @RequestMapping("/detail/{bookId}")
    public ModelAndView bookDetail(@PathVariable("bookId") long id) {
        ModelAndView modelAndView = new ModelAndView("book_detail");

        modelAndView.addObject("book", bookDao.findById(id));

        return modelAndView;
    }

    @RequestMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") Long id) {
        bookDao.deleteById(id);

        return "redirect:/library/success";
    }
}
