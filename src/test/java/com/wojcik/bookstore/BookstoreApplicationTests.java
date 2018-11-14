package com.wojcik.bookstore;

import com.wojcik.bookstore.entities.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void isClientNameSetting() {

        Client c = new Client();

        c.setFirstName("Patryk");

        Assert.assertTrue(c.getFirstName().equals("Patryk"));
    }
}
