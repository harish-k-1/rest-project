package com.example.restproject.service;

import com.example.restproject.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    //Acts as the Repository layer
    private List<Product> products = new ArrayList<>(
            Arrays.asList(
                    new Product("1", "Television", "Samsung", 21400.67, "S001"),
                    new Product("2", "Washing Machine", "LG", 14450.54, "L001"),
                    new Product("3", "Laptop", "Dell", 56950.83, "D001")));

    public List<Product> getProducts(String productId, String productName) {
        if(productId==null && productName!=null)
            return products.stream().filter(product -> product.getName().equalsIgnoreCase(productName)).collect(Collectors.toList());
        else if (productId!=null && productName==null) {
            return products.stream().filter(product -> product.getId().equalsIgnoreCase(productId)).collect(Collectors.toList());
        }
        return this.products;
    }

    public ResponseEntity<String> createProduct(Product product) {
        products.add(product);
        return ResponseEntity.ok().body("Product added");
    }

    public ResponseEntity<String> updateProduct(Product product, String id) {
        boolean found = false;
        for(int i = 0; i < this.products.size(); ++i) {
            if (products.get(i).getId().equals(id)) {
                this.products.set(i, product);
                found = true;
            }
        }
        return found ? ResponseEntity.ok().body("Product updated") : ResponseEntity.ok().body("Product not found");
    }

    public void deleteProduct(String id) {
        this.products.removeIf(p -> p.getId().equals(id));
    }
}
