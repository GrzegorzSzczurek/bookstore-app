package com.wojcik.bookstore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Book {

    @Id
    @Column
    private int id;

    @OneToOne
    @JoinColumn
    private Author author;

    @Column
    private String title;

    @Column
    private double price;

    @Column
    private int yearOfPublishment;

    @OneToMany(mappedBy = "book")
    private List<PurchaseDetails> purchaseDetails;

    public Book() {}

    public Book(Author author, String title, double price, int yearOfPublishment) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.yearOfPublishment = yearOfPublishment;
    }

    public Book(Author author, String title) {
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfPublishment() {
        return yearOfPublishment;
    }

    public void setYearOfPublishment(int yearOfPublishment) {
        this.yearOfPublishment = yearOfPublishment;
    }

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
