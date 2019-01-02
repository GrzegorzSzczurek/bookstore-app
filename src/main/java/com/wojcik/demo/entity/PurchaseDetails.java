package com.wojcik.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class PurchaseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn
    private Purchase purchase;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column
    private int quantity;

    @Column
    private float value;

    public PurchaseDetails() {}

    public PurchaseDetails(Book book) {
        this.book = book;
    }

    public PurchaseDetails(Purchase purchase, Book book) {
        this.purchase = purchase;
        this.book = book;
    }

    public PurchaseDetails(Purchase purchase, Book book, int quantity, float value) {
        this.purchase = purchase;
        this.book = book;
        this.quantity = quantity;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PurchaseDetails{" +
                "id=" + id +
                ", purchase=" + purchase.getId() +
                ", book=" + book.getId() +
                ", quantity=" + quantity +
                ", value=" + value +
                '}';
    }
}
