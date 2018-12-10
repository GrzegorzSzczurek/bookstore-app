package com.wojcik.demo.service;

import com.wojcik.demo.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User get(String username, String password);

    List<User> getUsers();
}
