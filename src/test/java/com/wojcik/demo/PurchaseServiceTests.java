package com.wojcik.demo;

import com.wojcik.demo.dao.PurchaseDAO;
import com.wojcik.demo.entity.Purchase;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.PurchaseServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PurchaseServiceTests {

    @Mock
    private PurchaseDAO purchaseRepository;
    @Mock
    private User user;
    @Mock
    private Purchase purchase;
    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSavePurchase() {
        Date date = new Date(2019, 01, 9);
        user = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        purchase = new Purchase(1, user, date);
        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        assertEquals(purchaseService.save(purchase), purchase);
    }

    @Test
    public void shouldGetUsersPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        Date date = new Date(2019, 01, 9);
        user = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        Purchase purchase = new Purchase(1, user, date);
        Purchase purchase2 = new Purchase(1, user, date);
        purchases.add(purchase);
        purchases.add(purchase2);

        when(purchaseRepository.getPurchasesByUser(user)).thenReturn(purchases);
        assertEquals(purchaseService.getPurchasesByUser(user), purchases);
    }
}
