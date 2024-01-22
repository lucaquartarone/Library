package org.example.library.repository;

import org.example.library.model.User;

import java.util.List;

public class UserRepository {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
