package com.example.restservice_demo.repositories;
import com.example.restservice_demo.entities.ProductDelivery;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductDeliveryRepository extends CrudRepository<ProductDelivery, String> {
    List<ProductDelivery> findByNameContaining(String name);

}
