package com.clothing_store.controller;

import com.clothing_store.dto.request.insert.ProductRequest;
import com.clothing_store.dto.request.update.ProductUpdateRequest;
import com.clothing_store.dto.response.ProductResponse;
import com.clothing_store.entity.product.Product;
import com.clothing_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @CrossOrigin
    ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully!");
    }

    @GetMapping
    @CrossOrigin
    ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/{productId}")
    @CrossOrigin
    ResponseEntity<List<ProductResponse>> getProduct(@PathVariable("productId") String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @PutMapping("/{productId}")
    @CrossOrigin
    ResponseEntity<String> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully!");
    }

    @DeleteMapping("/{productId}")
    @CrossOrigin
    ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    }
}
