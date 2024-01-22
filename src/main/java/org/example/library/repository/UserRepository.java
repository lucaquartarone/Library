package org.example.library.repository;

import org.example.library.model.User;

import java.util.List;

public class UserRepository {
    private List<User> usersList;

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
