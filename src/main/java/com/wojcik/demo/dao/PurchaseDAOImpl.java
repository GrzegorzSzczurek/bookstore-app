package com.wojcik.demo.dao;

import com.wojcik.demo.entity.Purchase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

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
}
