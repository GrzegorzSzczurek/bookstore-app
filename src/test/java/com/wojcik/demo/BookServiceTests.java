package com.wojcik.demo;

import com.wojcik.demo.dao.BookDAO;
import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BookServiceTests {

    @Mock
    private BookDAO bookRepository;
    @Mock
    private User user;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        createBooks();
    }

    @Test
    void shouldSaveBook() {
        when(bookRepository.save(book1)).thenReturn(book1);
        assertEquals(bookRepository.save(book1), book1);
    }

    @Test
    void shouldDeleteBookById() {
        int exampleBookId = book1.getId();
        when(bookRepository.getById(exampleBookId)).thenReturn(book1);
        bookService.remove(exampleBookId);
    }

    @Test
    void getBooks(){
        List<Book> books = new ArrayList<>();
        book1 = new Book(1, "J. K. Rowling", "Harry Potter", 1997, "Description", 24.0f);
        book2 = new Book(2, "Andrzej Sapkowski", "Wiedźmin", 1994, "Description", 24.0f);
        books.add(book1);
        books.add(book2);

        when(bookRepository.getBooks()).thenReturn(books);
        Assertions.assertEquals(books, bookService.getBooks());
    }

    @Test
    void getBookById() {
        when(bookRepository.getById(Mockito.anyInt())).thenReturn(book1);

        Assertions.assertEquals(book1.getId(), bookService.getById(1).getId());
    }


    @Test
    List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        book1 = new Book(1, "J. K. Rowling", "Harry Potter", 1997, "Description", 24.0f);
        book2 = new Book(2, "Andrzej Sapkowski", "Wiedźmin", 1994, "Description", 24.0f);
        books.add(book1);
        books.add(book2);
        return books;
    }

}
