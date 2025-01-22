package com.example.restservice_demo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    Optional<Product> findById(String id);

    //@Query("SELECT p FROM Products AS p WHERE p.name LIKE CONCAT('%', :username, '%')")
    List<Product> findByNameContaining(String name);
    List<Product> findByPrice(double price);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByInSight(boolean in_sight);
    List<Product> findByOrderByNameAsc();
    List<Product> findByOrderByPriceAsc();

}