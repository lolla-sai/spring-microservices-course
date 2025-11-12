package com.courses.spring_boot_microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.courses.spring_boot_microservices.product.ProductRequest.ProductRequest;
import com.courses.spring_boot_microservices.product.ProductResponse.ProductResponse;
import com.courses.spring_boot_microservices.product.model.Product;
import com.courses.spring_boot_microservices.product.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class ProductService {
    final ProductRepository productRepository;

    // we don't need the below as we use lombork logger
    // private static final org.slf4j.Logger log =
    // org.slf4j.LoggerFactory.getLogger(ProductService.class);

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product
                .builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        log.info("Product Created Succesfully!");

        productRepository.save(product);

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponse> listProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(
                        product -> new ProductResponse(product.getId(),
                                product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
