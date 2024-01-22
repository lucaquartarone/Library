package org.example.library.dao;

import org.example.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookDao extends CrudRepository<Book, Long> {
    @Query("select b from Book b where b.author = :author and b.title = :title")
    Book findByAuthorAndTitle(String author, String title);
    List<Book> findAllByTitle(String title);
    Book findById(long id);
}
