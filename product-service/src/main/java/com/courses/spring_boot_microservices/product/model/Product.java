package com.courses.spring_boot_microservices.product.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// @AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    private String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    // Alternatively, you could use lombok's AllArgsConstructor Annotation at the
    // top
    public Product(String id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
