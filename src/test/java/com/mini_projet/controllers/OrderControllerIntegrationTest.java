package com.mini_projet.controllers;

import com.mini_projet.entities.OrderEntity;
import com.mini_projet.entities.dto.request.OrderRequest;
import com.mini_projet.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderRequest orderRequest = new OrderRequest(1L, 2);
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\":1,\"quantity\":2}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"productId\":1,\"quantity\":2}"));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductId(1L);
        orderEntity.setQuantity(2);
        orderRepository.save(orderEntity);

        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect HTTP status 200
                .andExpect(content().json("[{\"id\":2,\"productId\":1,\"quantity\":2}]"));

    }
}
