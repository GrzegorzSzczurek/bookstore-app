package com.wojcik.demo.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @Size(min = 1, max = 150)
    @NotNull(message = "required")
    private String author;

    @Column
    @Size(min = 1, max = 200)
    @NotNull(message = "required")
    private String title;

    @Column
    @Max(2018)
    private int year;

    @Column
    private String description;

    @Column
    @Min(1)
    @Max(10000)
    private float price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PurchaseDetails> purchaseDetailsList;

    public Book() {
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title, int year, String description, float price) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.description = description;
        this.price = price;
    }

    public Book(int id, String author, String title, int year, String description, float price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.description = description;
        this.price = price;
    }



    public void addDetails(PurchaseDetails details) {

        if(purchaseDetailsList.isEmpty())
            purchaseDetailsList = new ArrayList<PurchaseDetails>();

        purchaseDetailsList.add(details);

        details.setBook(this);
    }

    public Book(int id, String author, String title, int year, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<PurchaseDetails> getPurchaseDetailsList() {
        return purchaseDetailsList;
    }

    public void setPurchaseDetailsList(List<PurchaseDetails> purchaseDetailsList) {
        this.purchaseDetailsList = purchaseDetailsList;
    }

    @Override
    public String toString() {
        return author + ": \"" + title + "\" (" + year + "). Price: " + price + "$";
    }

//    @Override
    public String toStringTechnical() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
