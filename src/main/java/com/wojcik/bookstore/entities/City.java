package com.wojcik.bookstore.entities;

import javax.persistence.*;

@Entity
@Table
public class City {

    @Id
    @Column(unique = true, length = 6)
    private String postalCode;

    @Column
    private String name;

    public City() {
    }

    public City(String postalCode, String name) {
        this.postalCode = postalCode;
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
