package com.example.order;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private StreamBridge streamBridge;

    public OrderService(OrderRepository orderRepository, StreamBridge streamBridge) {
        this.orderRepository = orderRepository;
        this.streamBridge = streamBridge;
    }

    public OrderInfo createOrder(OrderDTO order) {
        OrderEntity orderCreatedEntity = orderRepository.save(OrderEntity.builder()
                .products(order.getProducts())
                .customerName(order.getCustomerName())
                .totalPrice(order.getTotalPrice())
                .status(OrderStatus.PAYMENT_PENDING.toString())
                .build());
        streamBridge.send("order-created-out-0", MessageBuilder.withPayload(OrderEvent.builder()
                .orderId(orderCreatedEntity.getId())
                .eventId(UUID.randomUUID().toString())
                .eventType(EventType.ORDER_CREATED)
                .amount(orderCreatedEntity.getTotalPrice())
                .build()).build());
        return new OrderInfo(orderCreatedEntity.getId(), orderCreatedEntity.getStatus());
    }

    public Optional<OrderInfo> getOrder(String id) {
        return orderRepository.findById(id).map(orderEntity -> new OrderInfo(orderEntity.getId(), orderEntity.getStatus()));
    }
}
