package ru.work.orderservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.work.orderservice.dto.CreateOrderRequest;
import ru.work.orderservice.dto.CreateOrderResponse;
import ru.work.orderservice.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request){
        UUID orderId = orderService.createOrder(request);

        return new CreateOrderResponse(orderId, "CREATED");
    }
}
