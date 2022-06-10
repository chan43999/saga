package com.example.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("order")
@Data
@Builder
public class OrderEntity {

    @Id
    private String id;

    private String status;

    private List<String> products;

    private String customerName;

    private Integer totalPrice;
}
