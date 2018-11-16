package com.wojcik.bookstore.entities;

import javax.persistence.*;

@Entity
@Table
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne
    @JoinColumn
    private City city;

    @Column
    private String street;

    @Column
    private String homeNumber;

    public Adress() {
    }

    public Adress(City city, String street, String homeNumber) {
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public Adress(String street, String homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
