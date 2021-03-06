package com.example.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderNotFound> handleOrderNotFound(OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(OrderNotFound.builder().orderId(orderNotFoundException.getOrderId()).message("Cannot find order").build());
    }
}
