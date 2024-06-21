package com.mini_projet.services;

import com.mini_projet.entities.OrderEntity;
import com.mini_projet.entities.dto.request.OrderRequest;
import com.mini_projet.entities.dto.response.OrderResponse;
import com.mini_projet.mappers.OrderMapper;
import com.mini_projet.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;



    private OrderRequest orderRequest;
    private OrderEntity orderEntity;
    private OrderResponse orderResponse;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        orderRequest = new OrderRequest(1L, 2);
        orderEntity = new OrderEntity(1L, 1L, 2);
        orderResponse = new OrderResponse(1L, 1L, 2);

        when(orderMapper.toEntity(orderRequest)).thenReturn(orderEntity);
        when(orderMapper.toResponse(orderEntity)).thenReturn(orderResponse);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
    }

    @Test
    public void testCreateOrder() {
        OrderResponse result = orderService.createOrder(orderRequest);
        assertEquals(orderResponse, result);
    }

    @Test
    public void testGetTotalOrders() {
        orderService.createOrder(orderRequest);
        int totalOrders = orderService.getTotalOrders();
        assertEquals(1, totalOrders);
    }

    @Test
    public void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(orderEntity));
        List<OrderResponse> orders = orderService.getAllOrders();
        assertEquals(Collections.singletonList(orderResponse), orders);
    }
}
