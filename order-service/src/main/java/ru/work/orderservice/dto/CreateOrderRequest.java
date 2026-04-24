package ru.work.orderservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateOrderRequest(
        @NotBlank(message = "userId is required")
        String userId,

        @NotBlank(message = "productCode is required")
        String productCode,

        @Positive(message = "quantity must be greater than 0")
        int quantity,

        @DecimalMin(value = "0.01", message = "totalPrice must be greater than 0")
        BigDecimal totalPrice
) { }
