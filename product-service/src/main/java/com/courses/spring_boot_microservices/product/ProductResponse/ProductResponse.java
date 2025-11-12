package com.courses.spring_boot_microservices.product.ProductResponse;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description, BigDecimal price) {
}
