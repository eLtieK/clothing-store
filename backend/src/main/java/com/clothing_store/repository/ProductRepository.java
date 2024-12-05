package com.clothing_store.repository;

import com.clothing_store.dto.response.ProductResponse;
import com.clothing_store.entity.product.Product;
import com.clothing_store.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Procedure(procedureName = "InsertProduct")
    String insertProduct(@Param("p_description") String description,
                       @Param("p_name") String name,
                       @Param("p_price") BigDecimal price);

    @Procedure(procedureName = "UpdateProduct")
    void updateProduct(
            @Param("p_product_id") String product_id,
            @Param("p_price") BigDecimal price
    );

    @Procedure(procedureName = "InsertProductColor")
    void insertProductColor(@Param("p_product_id") String product_id,
                           @Param("p_color") String color);

    @Procedure(procedureName = "InsertProductSize")
    void insertProductSize(@Param("p_product_id") String product_id,
                           @Param("p_size") String size);

    @Procedure(procedureName = "GetAllProducts")
    List<Object> getAllProducts();

    @Procedure(procedureName = "GetProductById")
    List<Object> getProductById(@Param("p_product_id") String product_id);

    @Procedure(procedureName = "DeleteProduct")
    void deleteProduct(@Param("p_product_id") String product_id);
}
