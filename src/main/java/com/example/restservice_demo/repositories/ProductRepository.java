package com.example.restservice_demo.repositories;
import java.util.Optional;

import com.example.restservice_demo.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    Optional<Product> findById(String id);

    Page<Product> findAll(Pageable pageable);
    Page<Product> findByNameContaining(String name, Pageable pageable);
    Page<Product> findByNameContainingOrderByNameAsc(String name, Pageable pageable);
    Page<Product> findByNameContainingOrderByPriceAsc(String name, Pageable pageable);
    Page<Product> findByPriceOrderByNameAsc(double price, Pageable pageable);
    Page<Product> findByPriceOrderByPriceAsc(double price, Pageable pageable);
    Page<Product> findByPriceLessThan(double price, Pageable pageable);
    Page<Product> findByPriceLessThanOrderByNameAsc(double price, Pageable pageable);
    Page<Product> findByPriceLessThanOrderByPriceAsc(double price, Pageable pageable);
    Page<Product> findByPriceGreaterThan(double price, Pageable pageable);
    Page<Product> findByPriceGreaterThanOrderByNameAsc(double price, Pageable pageable);
    Page<Product> findByPriceGreaterThanOrderByPriceAsc(double price, Pageable pageable);
    Page<Product> findByInSight(boolean in_sight, Pageable pageable);
    Page<Product> findByInSightOrderByNameAsc(boolean in_sight, Pageable pageable);
    Page<Product> findByInSightOrderByPriceAsc(boolean in_sight, Pageable pageable);
    Page<Product> findByOrderByNameAsc(Pageable pageable);
    Page<Product> findByOrderByPriceAsc(Pageable pageable);
    Page<Product> findByPrice(double price, Pageable pageable);

    Page<Product> findByName(String productName, Pageable pageable);
}