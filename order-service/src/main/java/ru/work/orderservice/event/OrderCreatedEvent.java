package ru.work.orderservice.event;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        String userId,
        String productCode,
        int quantity,
        BigDecimal totalPrice,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Instant timestamp
) { }
