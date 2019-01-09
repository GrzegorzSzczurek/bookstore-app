package com.wojcik.demo;

import com.wojcik.demo.dao.PurchaseDetailsDAO;
import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.PurchaseDetails;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.PurchaseDetailsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseDetailsTests {

    @Mock
    private PurchaseDetailsDAO purchaseDetailsRepository;

    @Mock
    private PurchaseDetails purchaseDetails;
    @Mock
    private Purchase purchase;
    @Mock
    private Book book;
    @Mock
    private User user;

    @InjectMocks
    private PurchaseDetailsServiceImpl purchaseDetailsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSavePurchaseDetails() {
        Date date = new Date(2019, 01, 9);
        book = new Book(1, "J. K. Rowling", "Harry Potter", 1997, "Description", 24.0f);
        user = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        purchase = new Purchase(1, user, date);
        purchaseDetails = new PurchaseDetails(purchase, book);
        when(purchaseDetailsRepository.save(purchaseDetails)).thenReturn(purchaseDetails);
        assertEquals(purchaseDetailsService.save(purchaseDetails), purchaseDetails);
    }
}
