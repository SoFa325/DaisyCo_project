package com.example.restservice_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRep;

    public List<Product> filterBy(String filter){
        String[] s = filter.split("!");
        if (s.length != 2){
            return null;
        }
        filter = s[0];
        String field = s[1];
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
        return null;
    }

    public List<Product> sortBy(String sorter){
        switch (sorter) {
            case "name":
                return this.productRep.findByOrderByNameAsc();
            case "price":
                return this.productRep.findByOrderByPriceAsc();
            default:
                return null;
        }
    }

    public List<Product> filterByAndSort(String filter, String sorter){
        String[] s = filter.split("!");
        if (s.length != 2){
            return null;
        }
        filter = s[0];
        String field = s[1];
        try {
            switch (filter) {
                case "name":
                    switch (sorter) {
                        case "name":
                            return this.productRep.findByNameContainingOrderByNameAsc(s[1]);
                        case "price":
                            return this.productRep.findByNameContainingOrderByPriceAsc(s[1]);
                        default:
                            return this.productRep.findByNameContaining(s[1]);
                    }
                case "price":
                    if (field.charAt(0) == '>') {
                        double temp = Double.parseDouble(field.substring(1));
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceGreaterThanOrderByNameAsc(temp);
                            case "price":
                                return this.productRep.findByPriceGreaterThanOrderByPriceAsc(temp);
                            default:
                                return this.productRep.findByPriceGreaterThan(temp);
                        }
                    } else if (field.charAt(0) == '<') {
                        double temp = Double.parseDouble(field.substring(1));
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceLessThanOrderByNameAsc(temp);
                            case "price":
                                return this.productRep.findByPriceLessThanOrderByPriceAsc(temp);
                            default:
                                return this.productRep.findByPriceLessThan(temp);
                        }
                    } else {
                        double temp = Double.parseDouble(field);
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceOrderByNameAsc(temp);
                            case "price":
                                return this.productRep.findByPriceOrderByPriceAsc(temp);
                            default:
                                return this.productRep.findByPrice(temp);
                        }
                    }
                case "in_sight":
                    boolean temp = Boolean.parseBoolean(field);
                    switch (sorter) {
                        case "name":
                            return this.productRep.findByInSightOrderByNameAsc(temp);
                        case "price":
                            return this.productRep.findByInSightOrderByPriceAsc(temp);
                        default:
                            return this.productRep.findByInSight(temp);
                    }
            }
        } catch(NumberFormatException e){
            System.out.println(e);
            return null;
        }
        return null;
    }

}
