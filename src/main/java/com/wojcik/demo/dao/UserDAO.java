package com.wojcik.demo.dao;

import com.wojcik.demo.entity.User;

import java.util.List;

public interface UserDAO {

    void save(User user);

    User get(String username, String password);
    User get(String username);

    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);

    List<User> getUsers();

}