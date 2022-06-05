package com.example.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderNotFoundException extends RuntimeException {

    private String orderId;
}
