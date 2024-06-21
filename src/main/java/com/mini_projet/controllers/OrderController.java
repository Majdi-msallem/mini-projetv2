package com.mini_projet.controllers;


import com.mini_projet.entities.dto.request.OrderRequest;
import com.mini_projet.entities.dto.response.OrderResponse;
import com.mini_projet.producer.KafkaJsonProducer;
import com.mini_projet.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final KafkaJsonProducer kafkaJsonProducer;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse createdOrder = orderService.createOrder(request);
        kafkaJsonProducer.sendMessage(request);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }



    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
