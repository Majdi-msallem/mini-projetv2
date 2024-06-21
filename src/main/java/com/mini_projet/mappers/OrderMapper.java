package com.mini_projet.mappers;


import com.mini_projet.entities.OrderEntity;
import com.mini_projet.entities.dto.request.OrderRequest;
import com.mini_projet.entities.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderEntity toEntity(OrderRequest request) {
        return new OrderEntity(
                null,
                request.getProductId(),
                request.getQuantity()
        );
    }

    public OrderResponse toResponse(OrderEntity order) {
        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getQuantity()
        );
    }
}
