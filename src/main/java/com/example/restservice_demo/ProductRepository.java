package com.example.restservice_demo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findByName(String Name);

    Optional<Product> findById(String id);
}