package com.clothing_store.controller;

import com.clothing_store.dto.request.insert.ProductRequest;
import com.clothing_store.entity.Product;
import com.clothing_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    Product getProduct(@PathVariable("productId") String productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    Product updateProduct(@PathVariable String productId, @RequestBody ProductRequest request) {
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    String deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return "Product " + productId + " has been deleted";
    }
}
