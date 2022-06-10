package com.example.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private OrderService order;

    @Test
    void ShouldReturn1234WhenCreateOrderGivenOrderIdIs1234() {
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderEntity.builder()
                .id("1234")
                .build());
        when(streamBridge.send(any(String.class),any(Message.class))).thenReturn(true);

        OrderInfo orderInfo = order.createOrder(new OrderDTO());

        assertEquals("1234", orderInfo.getOrderId());
    }

    @Test
    void ShouldReturnOrder1234WhenFindById1234() {

        when(orderRepository.findById("1234")).thenReturn(Optional.of(OrderEntity.builder().id("1234").build()));

        Optional<OrderInfo> result = order.getOrder("1234");


        assertTrue(result.isPresent());
        assertEquals("1234", result.get().getOrderId());
    }
}