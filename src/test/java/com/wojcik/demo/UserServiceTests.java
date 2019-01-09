package com.wojcik.demo;

import com.wojcik.demo.dao.UserDAO;
import com.wojcik.demo.entity.User;
import com.wojcik.demo.service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Mock
    private UserDAO userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        createUsers();
    }

    @Test
    public void getUserByUsername(){
        User user1 = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        when(userRepository.get("nowaki")).thenReturn(user1);
        assertEquals(userService.get("nowaki").getUsername(), user1.getUsername());
    }

    @Test
    public void shouldSaveUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(userRepository.save(user1), user1);
    }

    @Test
    public void shouldDeleteUserById() {
        user1 = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        when(userRepository.get(user1.getUsername())).thenReturn(user1);
        userService.remove(user1.getId());
    }

    @Test
    public void shouldTakeEMail(){
        User user = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        assertFalse(userService.isEmailTaken(user.getEmail()));
    }

    @Test
    public void shouldTakeUsername(){
        User user = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        assertFalse(userService.isUsernameTaken(user.getUsername()));
    }

    @Test
    public void shouldGetUsers(){
        List<User> users = new ArrayList<>();
        user1 = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        user1 = new User(2, "Jan", "Kowalski", "email22@o2.pl", "kowalsky", "slabehaslo");
        users.add(user1);
        users.add(user2);

        when(userRepository.getUsers()).thenReturn(users);
        assertEquals(userService.getUsers(), users);
    }

    public List<User> createUsers(){
        List<User> users = new ArrayList<>();
        user1 = new User(1, "Adam", "Nowak", "email@o2.pl", "nowaki", "mocnehaslo");
        user1 = new User(2, "Jan", "Kowalski", "email22@o2.pl", "kowalsky", "slabehaslo");
        users.add(user1);
        users.add(user2);
        return users;
    }
}
