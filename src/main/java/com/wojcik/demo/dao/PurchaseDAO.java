package com.wojcik.demo.dao;

import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.User;

import java.util.List;

public interface PurchaseDAO {

    Purchase save(Purchase purchase);

    List<Purchase> getPurchasesByUser(User user);
}
