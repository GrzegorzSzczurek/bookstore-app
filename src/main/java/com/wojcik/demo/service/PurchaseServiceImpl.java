package com.wojcik.demo.service;

import com.wojcik.demo.dao.PurchaseDAO;
import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDAO purchaseDAO;

    @Override
    @Transactional
    public Purchase save(Purchase purchase) {
        purchaseDAO.save(purchase);
        return purchase;
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseDAO.getPurchasesByUser(user);
    }
}
