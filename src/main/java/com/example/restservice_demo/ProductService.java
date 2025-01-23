package com.example.restservice_demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRep;

    public Page<Product> filterBy(String filter, Pageable pageable){
        String[] s = filter.split("!");
        if (s.length != 2){
            return null;
        }
        filter = s[0];
        String field = s[1];
        try {
            switch (filter) {
                case "name":
                    return this.productRep.findByNameContaining(s[1], pageable);
                case "price":
                    if (field.charAt(0) == '>') {
                        return this.productRep.findByPriceGreaterThan(Double.parseDouble(field.substring(1)), pageable);
                    } else if (field.charAt(0) == '<') {
                        return this.productRep.findByPriceLessThan(Double.parseDouble(field.substring(1)), pageable);
                    } else {
                        return this.productRep.findByPrice(Double.parseDouble(field), pageable);
                    }
                case "in_sight":
                    return this.productRep.findByInSight(Boolean.parseBoolean(field), pageable);
            }
        } catch(NumberFormatException e){
            System.out.println(e);
            return null;
        }
        return null;
    }

    public Page<Product> sortBy(String sorter, Pageable pageable){
        switch (sorter) {
            case "name":
                return this.productRep.findByOrderByNameAsc(pageable);
            case "price":
                return this.productRep.findByOrderByPriceAsc(pageable);
            default:
                return null;
        }
    }

    public Page<Product> filterByAndSort(String filter, String sorter, Pageable pageable){
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
                            return this.productRep.findByNameContainingOrderByNameAsc(s[1], pageable);
                        case "price":
                            return this.productRep.findByNameContainingOrderByPriceAsc(s[1], pageable);
                        default:
                            return this.productRep.findByNameContaining(s[1], pageable);
                    }
                case "price":
                    if (field.charAt(0) == '>') {
                        double temp = Double.parseDouble(field.substring(1));
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceGreaterThanOrderByNameAsc(temp, pageable);
                            case "price":
                                return this.productRep.findByPriceGreaterThanOrderByPriceAsc(temp, pageable);
                            default:
                                return this.productRep.findByPriceGreaterThan(temp, pageable);
                        }
                    } else if (field.charAt(0) == '<') {
                        double temp = Double.parseDouble(field.substring(1));
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceLessThanOrderByNameAsc(temp, pageable);
                            case "price":
                                return this.productRep.findByPriceLessThanOrderByPriceAsc(temp, pageable);
                            default:
                                return this.productRep.findByPriceLessThan(temp, pageable);
                        }
                    } else {
                        double temp = Double.parseDouble(field);
                        switch (sorter) {
                            case "name":
                                return this.productRep.findByPriceOrderByNameAsc(temp, pageable);
                            case "price":
                                return this.productRep.findByPriceOrderByPriceAsc(temp, pageable);
                            default:
                                return this.productRep.findByPrice(temp, pageable);
                        }
                    }
                case "in_sight":
                    boolean temp = Boolean.parseBoolean(field);
                    switch (sorter) {
                        case "name":
                            return this.productRep.findByInSightOrderByNameAsc(temp, pageable);
                        case "price":
                            return this.productRep.findByInSightOrderByPriceAsc(temp, pageable);
                        default:
                            return this.productRep.findByInSight(temp, pageable);
                    }
            }
        } catch(NumberFormatException e){
            System.out.println(e);
            return null;
        }
        return null;
    }

}
