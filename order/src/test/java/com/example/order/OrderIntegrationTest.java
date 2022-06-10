package com.example.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void ShouldReturnOrderInfo1234WhenFindByOrderId1234() throws Exception {

        when(orderRepository.findById("1234")).thenReturn(Optional.of(OrderEntity.builder().id("1234").build()));

        mvc.perform(get("/orders/1234")).andExpect(status().isOk())
                .andExpect(jsonPath("orderId", is("1234")));
    }
}
