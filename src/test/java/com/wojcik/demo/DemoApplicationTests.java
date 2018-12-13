package com.wojcik.demo;

import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.UserService;
import com.wojcik.demo.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void isAdminUsernameTaken() {
        String username = "admin";

        boolean isTaken = userService.isUsernameTaken(username);

        Assert.assertTrue(isTaken);
    }

    @Test
    public void isUserFirstNameSettingCorrectly() {
        User user = new User();
        user.setFirstName("Patryk");

        Assert.assertTrue(user.getFirstName().equals("Patryk"));
    }

    @Test
    public void isUserLastNameSettingCorrectly() {
        User user = new User();
        user.setLastName("Wojcik");

        Assert.assertTrue(user.getLastName().equals("Wójcik"));
    }

}
