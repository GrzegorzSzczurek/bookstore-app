package com.wojcik.demo.entity;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @Size(min=1, max=30)
    @NotNull(message = "required")
    private String firstName;

    @Column
    @NotNull(message = "required")
    @Size(min=1, max=30)
    private String lastName;

    @Column
    @Email(message = "invalid e-mail")
    @NotNull(message = "required")
    private String email;

    @Column(unique = true)
    @NotNull(message = "required")
    @Size(min=1, max=24)
    private String username;

    @Column
    @NotNull(message = "required")
    @Size(min = 6, message = "min 6 chars")
    private String password;

    @Column
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public User() { this.admin = false; }

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

    public User(int id, String firstName, String lastName, @Email String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;

        this.admin = false;
    }

    public void addPurchase(Purchase purchase) {

        if(purchases.isEmpty())
            purchases = new ArrayList<Purchase>();

        purchases.add(purchase);

        purchase.setUser(this);
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

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        String s = "";

        if(this.isAdmin()) s = "Yes";
        else s = "No";

        return "ID: " + id +
                " | First name: " + firstName +
                " | Last name: " + lastName +
                " | E-mail: " + email +
                " | Username: " + username +
                " | Password: " + password +
                " | Is admin? " + s;
    }

//    @Override
    public String toStringTechnical() {
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
