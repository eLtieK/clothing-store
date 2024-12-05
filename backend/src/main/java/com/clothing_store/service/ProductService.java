package com.clothing_store.service;

import com.clothing_store.dto.request.insert.ProductRequest;
import com.clothing_store.entity.Product;
import com.clothing_store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public Product createProduct(ProductRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(new BigDecimal(request.getPrice()));
        product.setColors(request.getColors());
        product.setSizes(request.getSizes());

        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(String productId, ProductRequest request) {
        Product product = this.getProduct(productId);

        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            product.setPrice(new BigDecimal(request.getPrice()));
        }
        if (request.getColors() != null) {
            product.setColors(request.getColors());
        }
        if (request.getSizes() != null) {
            product.setSizes(request.getSizes());
        }

        return productRepository.save(product);
    }


    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
