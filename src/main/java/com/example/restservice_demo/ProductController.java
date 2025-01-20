package com.example.restservice_demo;


import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @RequestMapping(value="/create", produces="application/json")//http://localhost:8080/create?name=cavier&description=kvkvk&price=120&in_sight=true
    @ResponseBody
    public Product createProduct(@RequestParam("name") String name, @RequestParam(name = "description", defaultValue = "") String description, @RequestParam(name = "price", defaultValue = "0.0") double price,@RequestParam(defaultValue = "false") boolean in_sight) {
        if (name.length() >255){
            return null;
        }
        if (description.length() > 4096){
            description = description.substring(0, 4097);
        }
        if (price < 0.0){
            price = 0.0;
        }
        return new Product(name, description, price, in_sight);
    }
}