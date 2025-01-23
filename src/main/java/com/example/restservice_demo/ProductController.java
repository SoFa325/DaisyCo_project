package com.example.restservice_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRep;
    @Autowired
    private ProductService productService;


    @RequestMapping(value="/getProduct", produces="application/json")
    @ResponseBody
    public Optional<Product> getProduct(@RequestParam("name") String name) {
        return this.productRep.findById(name);
    }
    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public List<Product> getProducts(@RequestParam(name = "filterby", defaultValue = "") String filter, @RequestParam(name = "sortby", defaultValue = "") String sorter, @RequestParam(name="limit", defaultValue = "100") Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);
        String field;
        if (!filter.isEmpty() && !sorter.isEmpty()){
            return this.productService.filterByAndSort(filter, sorter, pageable).getContent();
        } else if(!filter.isEmpty()){
            return this.productService.filterBy(filter, pageable).getContent();
        }else if(!sorter.isEmpty()){
            return this.productService.sortBy(sorter, pageable).getContent();
        }
        return this.productRep.findAll(pageable).getContent();
    }

    @RequestMapping(value="/create")//http://localhost:8080/create?name=cavier&description=kvkvk&price=120&in_sight=true
    public void createProduct(@RequestParam("name") String name, @RequestParam(name = "description", defaultValue = "") String description, @RequestParam(name = "price", defaultValue = "0.0") double price,@RequestParam(defaultValue = "false") boolean in_sight) {
        Product p;
        if (name.length() <=255) {
            if (description.length() > 4096) {
                description = description.substring(0, 4097);
            }
            if (price < 0.0) {
                price = 0.0;
            }
            //System.out.println(name+description+price);
            p = new Product(name, description, price, in_sight);
            productRep.save(p);
        }
    }

    @RequestMapping(value="/update")//http://localhost:8080/update?name=cavier&property=price&value=12
    public void updateProduct(@RequestParam("name") String name, @RequestParam(name = "property") String property, @RequestParam(name = "value") String value) {
        Optional<Product> p = this.productRep.findById(name);
        if (p.isPresent()){
            //System.out.println("update"+ name);
            switch (property) {
                case "description":
                    p.get().setDescription(value);
                    this.productRep.save(p.get());
                    break;
                case "price":
                    p.get().setPrice(Double.parseDouble(value));
                    this.productRep.save(p.get());
                    break;
                case "in_sight":
                    p.get().setInSight(Boolean.parseBoolean(value));
                    this.productRep.save(p.get());
                    break;
            }
        }
    }

    @RequestMapping(value="/delete")//http://localhost:8080/delete?name=cavier
    public void deleteProduct(@RequestParam("name") String name) {
        this.productRep.deleteById(name);
    }
}