package com.mini_projet.services;

import com.mini_projet.entities.OrderEntity;
import com.mini_projet.entities.dto.request.OrderRequest;
import com.mini_projet.entities.dto.response.OrderResponse;
import com.mini_projet.mappers.OrderMapper;
import com.mini_projet.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final AtomicInteger totalOrders = new AtomicInteger(0);


    public OrderResponse createOrder(OrderRequest request) {
        OrderEntity order = orderMapper.toEntity(request);
        OrderEntity savedOrder = orderRepository.save(order);
        totalOrders.incrementAndGet(); // Incrémente le compteur des commandes

        return orderMapper.toResponse(savedOrder);
    }
    public int getTotalOrders() {
        return totalOrders.get();
    }

    public List<OrderResponse> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
