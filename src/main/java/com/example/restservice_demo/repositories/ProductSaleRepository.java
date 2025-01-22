package com.example.restservice_demo.repositories;
import com.example.restservice_demo.entities.ProductSale;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductSaleRepository extends CrudRepository<ProductSale, String> {
    List<ProductSale> findByNameContaining(String name);

}
