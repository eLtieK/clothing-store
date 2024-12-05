package com.clothing_store.service;

import com.clothing_store.dto.request.insert.ProductRequest;
import com.clothing_store.dto.request.update.ProductUpdateRequest;
import com.clothing_store.dto.response.ProductResponse;
import com.clothing_store.entity.product.Product;
import com.clothing_store.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createProduct(ProductRequest request) {
        String productId = productRepository.insertProduct(
                request.getDescription(),
                request.getName(),
                request.getPrice()
        );

        for (String color : request.getColors()) {
            productRepository.insertProductColor(
                    productId,
                    color
            );
        }

        for (String size : request.getSizes()) {
            productRepository.insertProductSize(
                    productId,
                    size
            );
        }
    }

    @Transactional
    public List<ProductResponse> getProducts() {
        List<Object> raw_list = productRepository.getAllProducts();
        List<ProductResponse> product_list = new ArrayList<>();

        for (Object raw_data : raw_list) {
            ProductResponse product_data = ProductResponse.convertToProductResponse(raw_data);
            product_list.add(product_data);
        }

        return product_list;
    }

    @Transactional
    public List<ProductResponse> getProduct(String productId) {
        List<Object> raw_list = productRepository.getProductById(productId);
        List<ProductResponse> product_list = new ArrayList<>();

        for (Object raw_data : raw_list) {
            ProductResponse product_data = ProductResponse.convertToProductResponse(raw_data);
            product_list.add(product_data);
        }

        return product_list;
    }

    @Transactional
    public void updateProduct(String productId, ProductUpdateRequest request) {
        productRepository.updateProduct(
                productId,
                request.getPrice()
        );
    }

    @Transactional
    public void deleteProduct(String productId) {
        productRepository.deleteProduct(productId);
    }
}
