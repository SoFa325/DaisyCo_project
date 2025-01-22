package com.example.restservice_demo.entities;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "varchar(4096)")
    private String description;
    @Column(columnDefinition = "double precision default 0.0 CHECK(price >= 0.0)")
    private double price;
    @Column(columnDefinition = "boolean default false")
    private boolean inSight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductDelivery> productDeliveries;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductSale> productSales;
    @Column(columnDefinition = "bigint default 0 CHECK(price >= 0)")
    private long amount;

    protected Product() {}

    public Product (String name, String description, double price, long amount){
        this.name = name;
        this.description = description;
        this.price = price;
        this.inSight = amount>0;
        this.amount = amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean isInSight() {
        return inSight;
    }

    public void setInSight(boolean inSight) {
        this.inSight = inSight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
