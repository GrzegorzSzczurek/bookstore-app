package com.wojcik.demo.service;

import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.User;

import java.util.List;

public interface PurchaseService {

    void save(Purchase purchase);

    List<Purchase> getPurchasesByUser(User user);
}
