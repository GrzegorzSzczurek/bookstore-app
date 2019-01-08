package com.wojcik.demo;

import com.wojcik.demo.entity.Book;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.UserService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserService userService;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
        user.setLastName("Wójcik");

        Assert.assertTrue(user.getLastName().equals("Wójcik"));
    }

    @Test
    public void authorIsNull(){
        Book book = new Book(null, "Harry Potter", 2000, "Opis", 24.00f);

        Set<ConstraintViolation<Book>> constraintViolations =
                validator.validate(book);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void authorIsEmpty(){
        Book book = new Book("", "Harry Potter", 2000, "Opis", 24.00f);

        Set<ConstraintViolation<Book>> constraintViolations =
                validator.validate(book);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 150",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void titleIsNull(){
        Book book = new Book("J. K. Rowling", null, 2000, "Opis", 24.00f);

        Set<ConstraintViolation<Book>> constraintViolations =
                validator.validate(book);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void titleIsEmpty(){
        Book book = new Book("J. K. Rowling", "", 2000, "Opis", 24.00f);

        Set<ConstraintViolation<Book>> constraintViolations =
                validator.validate(book);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 200",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void priceCostsZero(){
        Book book = new Book("J. K. Rowling", "Harry Potter", 2000, "Opis", 0);

        Set<ConstraintViolation<Book>> constraintViolations =
                validator.validate(book);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 10000",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void firstNameIsNull(){
        User user = new User(null, "Kowalsky", "mail@o2.pl", "username", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void firstNameIsEmty(){
        User user = new User("", "Kowalsky", "mail@o2.pl", "username", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 30",
                constraintViolations.iterator().next().getMessage()
        );
    }


    @Test
    public void lastNameIsNull(){
        User user = new User("Jan", null, "mail@o2.pl", "username", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void lastNameIsEmpty(){
        User user = new User("Jan", "", "mail@o2.pl", "username", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 30",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void emailIsNull(){
        User user = new User("Jan", "Kowalsky", null, "username", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void usernameIsNull(){
        User user = new User("Jan", "", "mail@o2.pl", null, "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 2, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void usernameIsEmpty(){
        User user = new User("Jan", "", "mail@o2.pl", "", "password");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 2, constraintViolations.size() );
        assertEquals(
                "size must be between 1 and 24",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void passwordIsNull(){
        User user = new User("Jan", "Kowalsky", "mail@o2.pl", "username", null);
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "required",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void passwordIsEmpty(){
        User user = new User("Jan", "", "mail@o2.pl", "username", "");
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user);

        assertEquals( 2, constraintViolations.size() );
        assertEquals(
                "min 6 chars",
                constraintViolations.iterator().next().getMessage()
        );
    }



}
