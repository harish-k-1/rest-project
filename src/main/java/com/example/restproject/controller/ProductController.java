package com.example.restproject.controller;

import com.example.restproject.model.Product;
import com.example.restproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public List<Product> getProductById(@RequestParam(name = "id", required = false) String productId,
                                          @RequestParam(name = "name", required = false) String productName) {
        return productService.getProducts(productId,productName);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/products/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(product, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
