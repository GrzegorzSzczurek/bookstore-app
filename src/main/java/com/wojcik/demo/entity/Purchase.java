package com.wojcik.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Column
    @NotNull(message = "required")
    private String paymentMethod;

    @Column
    @Pattern(regexp = "(?i)^[a-z0-9][a-z0-9\\- ]{0,10}[a-z0-9]$", message = "invalid postal code")
    @Size(min = 1, max = 15)
    @NotNull(message = "required")
    private String postalCode;

    @Column
    @Size(min = 1, max = 255)
    @NotNull(message = "required")
    private String city;

    @Column
    @Size(min = 1, max = 255)
    @NotNull(message = "required")
    private String street;

    @Column
    @Size(min = 1, max = 15)
    @NotNull(message = "required")
    private String homeNumber;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Purchase(int id, User user, Date date) {
        this.id = id;
        this.user = user;
        this.date = date;

        this.totalValue = 0;
    }



    public void addDetails(PurchaseDetails details) {

        if(purchaseDetailsList.isEmpty())
            purchaseDetailsList = new ArrayList<PurchaseDetails>();

        purchaseDetailsList.add(details);

        this.totalValue += details.getValue();

        details.setPurchase(this);
    }

    public void removeDetails(int id) {

        if(!purchaseDetailsList.isEmpty()) {

            this.totalValue -= purchaseDetailsList.get(id).getValue();

            purchaseDetailsList.remove(id);
        }
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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

    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetailsList;
    }

    public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
        this.purchaseDetailsList = purchaseDetails;
    }

    @Override
    public String toString() {
        return "Purchase number: " + id +
                ". Added on: " + date +
                ". Total value: " + totalValue + "$" +
                ". Payment method: " + paymentMethod +
                ". Address: " + postalCode + " " + city + ", " + street + " " + homeNumber +
                ". Book(s) purchased: " + purchaseDetailsList;

    }

//    @Override
    public String toStringTechnical() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", totalValue=" + totalValue +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", purchaseDetails='" + purchaseDetailsList + '\'' +
                '}';
    }
}
