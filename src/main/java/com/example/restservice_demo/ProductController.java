package com.example.restservice_demo;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {
    public HashMap<String, Product> products = new HashMap<>();

    @RequestMapping(value="/getProduct", produces="application/json")
    @ResponseBody
    public Product getProduct(@RequestParam("name") String name) {
        if (this.products.containsKey(name)){
            return this.products.get(name);
        } else{
            throw new ExceptionHandler.ThereIsNoSuchProductException();
        }
    }
    @RequestMapping(value="/get", produces="application/json")
    @ResponseBody
    public List<Product> getProducts() {
        return this.products.values().stream().toList();
    }

    @RequestMapping(value="/create")//http://localhost:8080/create?name=cavier&description=kvkvk&price=120&in_sight=true
    public void createProduct(@RequestParam("name") String name, @RequestParam(name = "description", defaultValue = "") String description, @RequestParam(name = "price", defaultValue = "0.0") String price,@RequestParam(defaultValue = "false") boolean in_sight) {
        if (name.length() <=255) {
            if (description.length() > 4096) {
                throw new ExceptionHandler.DescriptionShouldBeShorterException();
                //description = description.substring(0, 4097);
            }else {
                double dprice;
                try{
                    dprice = Double.parseDouble(price);
                } catch (NumberFormatException e) {
                    throw new ExceptionHandler.PriceShouldBeFloatingPointNumber();
                }
                if (dprice < 0.0) {
                    throw new ExceptionHandler.PriceShouldBeMoreThanZero();
                } else {
                    //System.out.println("create"+ name);
                    if (this.products.containsKey(name)) {
                        this.products.get(name).update(description, dprice, in_sight);
                    } else {
                        this.products.put(name, new Product(name, description, dprice, in_sight));
                    }
                }
            }
        }else{
            throw new ExceptionHandler.NameShouldBeShorterException();
        }
    }

    @RequestMapping(value="/update")//http://localhost:8080/update?name=cavier&property=price&value=12
    public void updateProduct(@RequestParam("name") String name, @RequestParam(name = "property") String property, @RequestParam(name = "value") String value) {
        if (this.products.containsKey(name)){
            //System.out.println("update"+ name);
            switch (property) {
                case "description":
                    if (value.length() > 4096) {
                        throw new ExceptionHandler.DescriptionShouldBeShorterException();
                        //description = description.substring(0, 4097);
                    }else {
                        this.products.get(name).setDescription(value);
                    }
                    break;
                case "price":
                    double dprice;
                    try{
                        dprice = Double.parseDouble(value);
                    } catch (NumberFormatException e) {
                        throw new ExceptionHandler.PriceShouldBeFloatingPointNumber();
                    }
                    if (dprice < 0.0) {
                        throw new ExceptionHandler.PriceShouldBeMoreThanZero();
                    } else {
                        this.products.get(name).setPrice(Double.parseDouble(value));
                    }
                    break;
                case "in_sight":
                    this.products.get(name).setIn_sight(Boolean.parseBoolean(value));
                    break;
                default:
                    throw new ExceptionHandler.WrongProperty();
            }
        } else{
            throw new ExceptionHandler.ThereIsNoSuchProductException();
        }
    }

    @RequestMapping(value="/delete")//http://localhost:8080/delete?name=cavier
    public void deleteProduct(@RequestParam("name") String name) {
        if (this.products.containsKey(name)){
            //System.out.println("delete"+ name);
            this.products.remove(name);
        }else{
            throw new ExceptionHandler.ThereIsNoSuchProductException();
        }
    }
}