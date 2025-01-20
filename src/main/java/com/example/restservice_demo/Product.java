package com.example.restservice_demo;

public class Product {
    String name;
    String description;
    double price;
    boolean in_sight;
    Product (String name, String description, double price, boolean in_sight){
        this.name = name;
        this.description = description;
        this.price = price;
        this.in_sight = in_sight;
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

    public boolean isIn_sight() {
        return in_sight;
    }

    public void setIn_sight(boolean in_sight) {
        this.in_sight = in_sight;
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
        this.in_sight = in_sight;
    }


}
