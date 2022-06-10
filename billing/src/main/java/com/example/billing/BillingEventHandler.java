package com.example.billing;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Component
@Slf4j
public class BillingEventHandler {

    private BillRepository billRepository;

    public BillingEventHandler(BillRepository billRepository) {
        this.billRepository = billRepository;
    }


    @Bean
    public Function<KStream<Object,OrderEvent>,KStream<Object,BillEvent>> order() {

        return message -> message.map((key, order) -> {

            Bill saveBill = billRepository.save(new Bill(order.getOrderId(), Optional.ofNullable(order.getAmount()).orElse(0), false));
            return new KeyValue<>(null, BillEvent.builder()
                    .eventId(UUID.randomUUID().toString())
                    .billId(saveBill.getId())
                    .orderId(saveBill.getOrderId())
                    .amount(saveBill.getTotalPrice())
                    .status(false)
                    .bankName("")
                    .build());
        });
    }

}
