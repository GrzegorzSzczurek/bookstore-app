package com.wojcik.bookstore.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase_details")
public class PurchaseDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    public PurchaseDetails() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Purchase getPurchase() { return purchase; }

    public void setPurchase(Purchase purchase) { this.purchase = purchase; }

    public Book getBook() {  return book; }

    public void setBook(Book book) { this.book = book; }

    @Override
    public String toString() {
        return "PurchaseDetails{" +
                "id=" + id +
                ", purchase=" + purchase +
                ", book=" + book +
                '}';
    }
}
