package com.wojcik.bookstore.repositories;

import com.wojcik.bookstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
