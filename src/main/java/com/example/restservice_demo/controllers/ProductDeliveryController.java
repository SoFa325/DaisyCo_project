package com.example.restservice_demo.controllers;
import com.example.restservice_demo.entities.ProductDelivery;
import com.example.restservice_demo.repositories.ProductDeliveryRepository;
import com.example.restservice_demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/productDelivery")
public class ProductDeliveryController {
    @Autowired
    private ProductDeliveryRepository deliveryRep;
    @Autowired
    private ProductRepository productRep;
    @RequestMapping(value="/getProductDelivery", produces="application/json")
    @ResponseBody
    public Optional<ProductDelivery> getProductDelivery(@RequestParam("name") String name) {
        return this.deliveryRep.findById(name);
    }

    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public Iterable<ProductDelivery> getProductDeliveries() {
        return this.deliveryRep.findAll();
    }

    @RequestMapping(value="/create")//http://localhost:8080/productDelivery/create?name=caviardel&productName=cavier&amount=4
    public void createProductDelivery(@RequestParam("name") String name, @RequestParam(name = "productName") String productName, @RequestParam(name = "amount") long amount) {
        ProductDelivery d;
        if (name.length() <=255 && amount >0) {
            d = new ProductDelivery(name,this.productRep.findByName(productName), amount);
            deliveryRep.save(d);
        }
    }
    @RequestMapping(value="/update")//http://localhost:8080/productDelivery/update?name=caviardel&property=amount&value=120
    public void updateProductDelivery(@RequestParam("name") String name, @RequestParam(name = "property") String property, @RequestParam(name = "value") String value) {
        Optional<ProductDelivery> d = this.deliveryRep.findById(name);
        if (d.isPresent()){
            //System.out.println("update"+ name);
            switch (property) {
                case "productName":
                    d.get().setProduct(this.productRep.findByName(value));
                    this.deliveryRep.save(d.get());
                    break;
                case "amount":
                    d.get().setAmount(Long.parseLong(value));
                    this.deliveryRep.save(d.get());
                    break;
            }
        }
    }

    @RequestMapping(value="/delete")
    public void deleteProductDelivery(@RequestParam("name") String name) {
        this.deliveryRep.deleteById(name);
    }

}
