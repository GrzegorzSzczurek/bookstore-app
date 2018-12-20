package com.wojcik.demo.dao;

import com.wojcik.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
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
    public void remove(int userId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        session.beginTransaction();

        Query query = session.createQuery("delete from User where id = " + userId);
        query.executeUpdate();

        session.getTransaction().commit();

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
    public User get(String username) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<User> users = session.createQuery("from User u where u.username='"+username+"'").getResultList();
        session.close();

        if(users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        User user = this.get(username);

        if(user == null) return false;
        return true;
    }

    @Override
    public boolean isEmailTaken(String email) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<User> users = session.createQuery("from User u where u.email='"+email+"'").getResultList();
        session.close();

        if(users.isEmpty()) return false;
        return true;
    }

    @Override
    public List<User> getUsers() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<User> users = session.createQuery("from User").getResultList();
        session.close();

        return users;
    }
}
