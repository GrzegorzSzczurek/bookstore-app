package com.wojcik.demo.dao;

import com.wojcik.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(user);
        session.close();
    }

    @Override
    public User get(String username, String password) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<User> users = session.createQuery("from User u where u.username='"+username+"' and u.password='"+password+"'").getResultList();
        session.close();

        if(users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public List<User> getUsers() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<User> users = session.createQuery("from User").getResultList();
        session.close();

        return users;
    }
}
