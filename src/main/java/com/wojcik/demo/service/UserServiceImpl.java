package com.wojcik.demo.service;

import com.wojcik.demo.dao.UserDAO;
import com.wojcik.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void remove(int userId) {
        userDAO.remove(userId);
    }

    @Override
    @Transactional
    public User get(String username, String password) {
        return userDAO.get(username, password);
    }

    @Override
    @Transactional
    public User get(String username) {
        return userDAO.get(username);
    }

    @Override
    @Transactional
    public boolean isUsernameTaken(String username) {
        return userDAO.isUsernameTaken(username);
    }

    @Override
    @Transactional
    public boolean isEmailTaken(String email) {
        return userDAO.isEmailTaken(email);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
