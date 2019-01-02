package com.wojcik.demo.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private Date date;

    @Column
    private float totalValue;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchaseDetails> purchaseDetailsList;

    public Purchase() {
        this.totalValue = 0;
        purchaseDetailsList = new ArrayList<PurchaseDetails>();
    }

    public Purchase(User user, Date date) {
        this.user = user;
        this.date = date;

        this.totalValue = 0;
    }

    public void addDetails(PurchaseDetails details) {

        if(purchaseDetailsList.isEmpty())
            purchaseDetailsList = new ArrayList<PurchaseDetails>();

        purchaseDetailsList.add(details);

        details.setPurchase(this);
    }

    public void removeDetails(int id) {

        if(!purchaseDetailsList.isEmpty())
            purchaseDetailsList.remove(id);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetailsList;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetailsList = purchaseDetails;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", user=" + user +
                ", date=" + date +
                ", totalValue=" + totalValue +
                '}';
    }
}
