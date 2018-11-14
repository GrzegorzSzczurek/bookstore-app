package com.wojcik.bookstore.repositories;

import com.wojcik.bookstore.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
