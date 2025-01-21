package com.example.restservice_demo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {


    Optional<Product> findById(String id);
}