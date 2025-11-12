package com.courses.spring_boot_microservices.order.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        String orderNumber,
        @NotBlank @NotEmpty String skuCode,
        @Positive @NotNull BigDecimal price,
        @Positive @NotNull Integer quantity) {

}
