package com.wojcik.bookstore.repositories;

import com.wojcik.bookstore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
