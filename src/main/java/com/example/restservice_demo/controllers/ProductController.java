package com.example.restservice_demo.controllers;
import com.example.restservice_demo.entities.Product;
import com.example.restservice_demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRep;

    @RequestMapping(value="/getProduct", produces="application/json")
    @ResponseBody
    public Optional<Product> getProduct(@RequestParam("name") String name) {
        return this.productRep.findById(name);
    }
    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public Iterable<Product> getProducts(@RequestParam(name = "filterby", defaultValue = "") String filter, @RequestParam(name = "sortby", defaultValue = "") String sorter) {
        String field;
        if (!filter.isEmpty() && !sorter.isEmpty()){
            //TODO
        } else if(!filter.isEmpty()){
            String[] s = filter.split("!");
            if (s.length != 2){
                return null;
            }
            filter = s[0];
            field = s[1];
            try {
                switch (filter) {
                    case "name":
                        return this.productRep.findByNameContaining(s[1]);
                    case "price":
                        if (field.charAt(0) == '>') {
                            return this.productRep.findByPriceGreaterThan(Double.parseDouble(field.substring(1)));
                        } else if (field.charAt(0) == '<') {
                            return this.productRep.findByPriceLessThan(Double.parseDouble(field.substring(1)));
                        } else {
                            return this.productRep.findByPrice(Double.parseDouble(field));
                        }
                    case "in_sight":
                        return this.productRep.findByInSight(Boolean.parseBoolean(field));
                }
            } catch(NumberFormatException e){
                System.out.println(e);
                return null;
            }
        }else if(!sorter.isEmpty()){
            switch (sorter) {
                case "name":
                    return this.productRep.findByOrderByNameAsc();
                case "price":
                    return this.productRep.findByOrderByPriceAsc();
                default:
                    return null;
            }
        }
        return this.productRep.findAll();
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