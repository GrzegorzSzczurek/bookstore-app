package com.wojcik.bookstore.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Purchase {

    @Id
    @Column
    private int id;

    @Column
    private Date date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn
    private Client client;

    @Column
    private double totalValue;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetails> purchaseDetails;

    public Purchase() {
    }

    public Purchase(Date date, Client client) {
        this.date = date;
        this.client = client;
    }

    public Purchase(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
