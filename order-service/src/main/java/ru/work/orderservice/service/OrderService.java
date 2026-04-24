package ru.work.orderservice.service;

import org.springframework.stereotype.Service;
import ru.work.orderservice.dto.CreateOrderRequest;
import ru.work.orderservice.event.OrderCreatedEvent;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderProducer orderProducer;

    public OrderService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    public UUID createOrder(CreateOrderRequest request) {
        UUID orderId =  UUID.randomUUID();

        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                orderId,
                request.userId(),
                request.productCode(),
                request.quantity(),
                request.totalPrice(),
                Instant.now()
        );

        orderProducer.send(orderCreatedEvent);

        return orderId;
    }
}
