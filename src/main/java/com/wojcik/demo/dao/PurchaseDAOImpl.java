package com.wojcik.demo.dao;

import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(Purchase purchase) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(purchase);
        session.close();
    }

    @Override
    public List<Purchase> getPurchasesByUser(User user) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Purchase> purchases = session.createQuery("from Purchase p where p.user.id=" + user.getId()).getResultList();
        session.close();

        return purchases;
    }
}
