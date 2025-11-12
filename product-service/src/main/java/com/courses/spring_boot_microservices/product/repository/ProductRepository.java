package com.courses.spring_boot_microservices.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courses.spring_boot_microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
