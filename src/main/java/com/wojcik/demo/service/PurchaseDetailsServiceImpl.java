package com.wojcik.demo.service;

import com.wojcik.demo.dao.PurchaseDetailsDAO;
import com.wojcik.demo.entity.PurchaseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {

    @Autowired
    private PurchaseDetailsDAO purchaseDetailsDAO;

    @Override
    public void save(PurchaseDetails purchaseDetails) {
        purchaseDetailsDAO.save(purchaseDetails);
    }
}
