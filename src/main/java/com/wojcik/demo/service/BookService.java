package com.wojcik.demo.service;

import com.wojcik.demo.entity.Book;

import java.util.List;

public interface BookService {

    void save(Book book);

    void remove(int bookId);

    Book getById(int id);

    List<Book> getBooks();

    List<Book> getBestsellers();
}
