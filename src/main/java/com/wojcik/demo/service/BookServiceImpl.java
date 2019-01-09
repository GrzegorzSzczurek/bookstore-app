package com.wojcik.demo.service;

import com.wojcik.demo.dao.BookDAO;
import com.wojcik.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void remove(int bookId) {
        bookDAO.remove(bookId);
    }

    @Override
    @Transactional
    public Book getById(int id) {
        return bookDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public List<Book> getBestsellers() {
        return bookDAO.getBestsellers();
    }
}
