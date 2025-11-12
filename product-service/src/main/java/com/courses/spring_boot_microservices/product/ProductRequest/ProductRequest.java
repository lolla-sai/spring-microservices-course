package com.courses.spring_boot_microservices.product.ProductRequest;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "Product name cannot be blank") String name,
        @NotBlank(message = "Product name cannot be blank") String description,
        @NotNull(message = "Price cannot be null") @Positive(message = "Price must be a positive value") BigDecimal price) {
}
