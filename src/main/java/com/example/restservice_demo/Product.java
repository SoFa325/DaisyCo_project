package com.example.restservice_demo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    String name;
    @Column(columnDefinition = "varchar(4096)")
    String description;
    @Column(columnDefinition = "double precision default 0.0 CHECK(price >= 0.0)")
    double price;
    @Column(columnDefinition = "boolean default false")
    boolean inSight;

    protected Product() {}

    Product (String name, String description, double price, boolean inSight){
        this.name = name;
        this.description = description;
        this.price = price;
        this.inSight = inSight;
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

    public void update(String description, double price, boolean in_sight){
        this.description = description;
        this.price = price;
        this.inSight = in_sight;
    }


}
