package com.wojcik.demo.service;

import com.wojcik.demo.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void remove(int userId);

    User get(String username, String password);
    User get(String username);

    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);

    List<User> getUsers();
}
