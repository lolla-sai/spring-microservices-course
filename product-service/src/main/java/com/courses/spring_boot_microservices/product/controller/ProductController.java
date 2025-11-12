package com.courses.spring_boot_microservices.product.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.courses.spring_boot_microservices.product.ProductRequest.ProductRequest;
import com.courses.spring_boot_microservices.product.ProductResponse.ProductResponse;
import com.courses.spring_boot_microservices.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    final ProductService productService;

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "Product Service is up!");
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }

    @PostMapping("/")
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return response;
    }

    @GetMapping("/")
    public List<ProductResponse> listProducts() {
        return productService.listProducts();
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@RequestParam String id) {
        productService.deleteProductById(id);
    }

    // delete all products
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
}
