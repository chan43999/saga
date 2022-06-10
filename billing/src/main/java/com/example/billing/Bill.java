package com.example.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("Bill")
@RequiredArgsConstructor
public class Bill {

    @Id
    private String id;

    @NonNull
    private String orderId;

    @NonNull
    private Integer totalPrice;

    @NonNull
    private Boolean billingStatus;

    private String bankName;
}
