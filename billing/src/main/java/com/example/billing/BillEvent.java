package com.example.billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BillEvent {

    private String eventId;

    private String billId;

    private String orderId;

    private Integer amount;

    private String bankName;

    private Boolean status;
}