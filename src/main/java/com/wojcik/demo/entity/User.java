package com.wojcik.demo.entity;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @Size(min = 1, max = 30)
    @NotNull(message = "required")
    private String firstName;

    @Column
    @NotNull(message = "required")
    @Size(min = 1, max = 30)
    private String lastName;

    @Column
    @Email
    @NotNull(message = "required")
    private String email;

    @Column(unique = true)
    @Size(min = 1, max = 24)
    @NotNull(message = "required")
    private String username;

    @Column
    @NotNull(message = "required")
    @Size(min = 6, message = "min 6 chars")
    private String password;

    @Column
    private boolean admin;

    public User() {
        this.admin = false;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        this.admin = false;
    }

    public User(String firstName, String lastName, @Email String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;

        this.admin = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}
