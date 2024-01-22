package org.example.library.repository;

import org.example.library.model.Book;

import java.util.List;

public class BookRepository {
    private List<Book> booksList;

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }
}
