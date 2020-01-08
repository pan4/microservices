package com.dataart.edu.ms.product.domain.dto;

public class EstablishProductDto {
    private String productId = "";
    private Boolean successfully = false;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(Boolean successfully) {
        this.successfully = successfully;
    }
}
