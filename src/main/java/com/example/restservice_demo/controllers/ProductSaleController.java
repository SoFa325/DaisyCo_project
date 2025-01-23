package com.example.restservice_demo.controllers;
import com.example.restservice_demo.entities.Product;
import com.example.restservice_demo.entities.ProductSale;
import com.example.restservice_demo.repositories.ProductRepository;
import com.example.restservice_demo.repositories.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/productSale")
public class ProductSaleController {
    @Autowired
    private ProductSaleRepository saleRep;
    @Autowired
    private ProductRepository productRep;
    @RequestMapping(value="/getProductSale", produces="application/json")
    @ResponseBody
    public Optional<ProductSale> getProductSale(@RequestParam("name") String name) {
        return this.saleRep.findById(name);
    }

    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public Iterable<ProductSale> getProductSales() {
        return this.saleRep.findAll();
    }

    @RequestMapping(value="/create")//http://localhost:8080/productDelivery/create?name=caviardel&productName=cavier&amount=4
    public void createProductSale(@RequestParam("name") String name, @RequestParam(name = "productName") String productName, @RequestParam(name = "amount") long amount) {
        ProductSale s;
        if (name.length() <=255 && amount >0) {
            Product p = this.productRep.findByName(productName, PageRequest.of(0, 100)).getContent().get(0);
            long a = p.getAmount()-amount;
            if (a>=0) {
                p.setInSight(a>0);
                //System.out.println(p.getAmount());
                p.setAmount(p.getAmount() - amount);
                //System.out.println(p.getAmount());
                s = new ProductSale(name, p, amount);
                saleRep.save(s);
            }
        }
    }
    @RequestMapping(value="/update")//http://localhost:8080/productDelivery/update?name=caviardel&property=amount&value=120
    public void updateProductSale(@RequestParam("name") String name, @RequestParam(name = "property") String property, @RequestParam(name = "value") String value) {
        Optional<ProductSale> s = this.saleRep.findById(name);
        if (s.isPresent()){
            //System.out.println("update"+ name);
            switch (property) {
                case "productName":
                    s.get().setProduct(this.productRep.findByName(value, PageRequest.of(0, 100)).getContent().get(0));
                    this.saleRep.save(s.get());
                    break;
                case "amount":
                    s.get().setAmount(Long.parseLong(value));
                    this.saleRep.save(s.get());
                    break;
            }
        }
    }

    @RequestMapping(value="/delete")
    public void deleteProductSale(@RequestParam("name") String name) {
        this.saleRep.deleteById(name);
    }

}
