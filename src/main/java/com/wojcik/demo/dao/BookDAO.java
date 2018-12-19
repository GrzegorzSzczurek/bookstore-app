package com.wojcik.demo.dao;

import com.wojcik.demo.entity.Book;

import java.util.List;

public interface BookDAO {

    void save(Book book);

    void remove(int bookId);

    Book getById(int id);

    List<Book> getBooks();
}
