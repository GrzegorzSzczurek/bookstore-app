package com.wojcik.bookstore.repositories;

import com.wojcik.bookstore.entities.Purchase;
import com.wojcik.bookstore.entities.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, Integer> {
}
