package com.wojcik.bookstore;

import com.wojcik.bookstore.entities.*;
import com.wojcik.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
