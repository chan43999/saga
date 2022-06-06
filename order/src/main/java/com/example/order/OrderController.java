package com.example.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfo createOrder(@Valid @RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/orders/{id}")
    public OrderInfo getOrder(@PathVariable String id) throws OrderNotFoundException {
        return orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException(id));
    }
}
