package com.example.restservice_demo.entities;
import jakarta.persistence.*;

@Entity
@Table(name="productsale")
public class ProductSale {
    @Id
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productid", unique = true, nullable = false)
    private Product product;
    @Column(columnDefinition = "bigint CHECK(amount > 0)")
    private long amount;
    @Column(columnDefinition = "double precision")
    private double price;

    protected ProductSale() {}

    public ProductSale (String name, Product pr, long amount){
        this.name = name;
        this.product = pr;
        this.amount = amount;
        this.price = pr.getPrice()*this.amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
