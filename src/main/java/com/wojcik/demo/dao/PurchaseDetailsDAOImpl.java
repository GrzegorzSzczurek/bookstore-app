package com.wojcik.demo.dao;

import com.wojcik.demo.entity.PurchaseDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class PurchaseDetailsDAOImpl implements PurchaseDetailsDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(PurchaseDetails purchaseDetails) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        session.save(purchaseDetails);
        session.close();
    }
}
