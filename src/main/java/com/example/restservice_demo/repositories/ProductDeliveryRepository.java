package com.example.restservice_demo.repositories;
import com.example.restservice_demo.entities.Product;
import com.example.restservice_demo.entities.ProductDelivery;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductDeliveryRepository extends CrudRepository<ProductDelivery, String> {
    List<ProductDelivery> findByNameContaining(String name);

}
