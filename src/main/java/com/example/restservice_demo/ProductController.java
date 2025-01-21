package com.example.restservice_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {
    //HashMap<String, Product> products = new HashMap<>();
    @Autowired
    private ProductRepository productRep;

    /*@RequestMapping(value="/getProduct", produces="application/json")
    @ResponseBody
    public Product getProduct(@RequestParam("name") String name) {
        if (this.products.containsKey(name)){
            return this.products.get(name);
        }
        return null;
    }
    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public List<Product> getProducts() {
        return this.products.values().stream().toList();
    }*/

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

            p = new Product(name, description, price, in_sight);
            productRep.save(p);
        }
    }

    /*@RequestMapping(value="/update")//http://localhost:8080/update?name=cavier&property=price&value=12
    public void updateProduct(@RequestParam("name") String name, @RequestParam(name = "property") String property, @RequestParam(name = "value") String value) {
        if (this.products.containsKey(name)){
            //System.out.println("update"+ name);
            switch (property) {
                case "description":
                    this.products.get(name).setDescription(value);
                    break;
                case "price":
                    this.products.get(name).setPrice(Double.parseDouble(value));
                    break;
                case "in_sight":
                    this.products.get(name).setIn_sight(Boolean.parseBoolean(value));
                    break;
            }
        }
    }

    @RequestMapping(value="/delete")//http://localhost:8080/delete?name=cavier
    public void deleteProduct(@RequestParam("name") String name) {
        if (this.products.containsKey(name)){
            //System.out.println("delete"+ name);
            this.products.remove(name);
        }
    }*/
}