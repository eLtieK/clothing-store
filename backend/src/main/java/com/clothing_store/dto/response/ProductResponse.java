package com.clothing_store.dto.response;

import java.math.BigDecimal;

public class ProductResponse {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String color;
    private String size;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public static ProductResponse convertToProductResponse(Object data) {
        ProductResponse response = new ProductResponse();

        // Kiểm tra nếu đối tượng là kiểu mảng Object[]
        if (data instanceof Object[]) {
            Object[] row = (Object[]) data;

            // Ánh xạ các giá trị vào ProductResponse
            response.setProductId((String) row[0]); // productId
            response.setName((String) row[1]); // name
            response.setDescription((String) row[2]); // description
            response.setPrice((BigDecimal) row[3]); // price
            response.setColor((String) row[4]); // color
            response.setSize((String) row[5]); // size
        }

        return response;
    }
}
