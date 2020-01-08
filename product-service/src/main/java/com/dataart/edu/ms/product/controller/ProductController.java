package com.dataart.edu.ms.product.controller;

import com.dataart.edu.ms.product.domain.Product;
import com.dataart.edu.ms.product.domain.dto.EstablishProductDto;
import com.dataart.edu.ms.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        this.productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/establish_product")
    public ResponseEntity<EstablishProductDto> establishProduct(@RequestBody EstablishProductDto establishProductDto) {
        this.productService.establishProduct(establishProductDto.getProductId());
        establishProductDto.setSuccessfully(true);
        return ResponseEntity.ok(establishProductDto);
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(this.productService.list());
    }
}
