package com.example.billing;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class BillingEventHandler {


    @Bean
    public Consumer<KStream<Object,String>> order() {

        return input -> input.foreach((k,v) -> log.info(v));
    }

}
