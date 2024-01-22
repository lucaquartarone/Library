package org.example.library.dao;

import org.example.library.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
    @Query("select u from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(String username, String password);
    User findById(long id);
    User findByUsername(String username);
}
