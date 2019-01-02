package com.wojcik.demo.service;

import com.wojcik.demo.dao.PurchaseDAO;
import com.wojcik.demo.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDAO purchaseDAO;

    @Override
    @Transactional
    public void save(Purchase purchase) {
        purchaseDAO.save(purchase);
    }
}
