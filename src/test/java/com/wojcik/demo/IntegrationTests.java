package com.wojcik.demo;

import com.wojcik.demo.dao.BookDAO;
import com.wojcik.demo.entity.Book;
import com.wojcik.demo.service.BookService;
import com.wojcik.demo.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class IntegrationTests {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public BookService bookService() {
            return new BookServiceImpl();
        }

    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDAO bookRepository;

    @Before
    public void setUp() {
        Book potter = new Book(1, "alex", "potter", 1994, "desc", 24.00f);

        Mockito.when(bookRepository.getById(potter.getId()))
                .thenReturn(potter);
    }

    @Test
    public void whenValidId_thenBookShouldBeFound() {
        int bookId = 1;
        Book bookFound = bookService.getById(1);

        assertThat(bookFound.getId())
                .isEqualTo(bookId);
    }
}
