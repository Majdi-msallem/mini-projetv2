package com.mini_projet.producer;


import com.mini_projet.entities.OrderEntity;
import com.mini_projet.entities.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaJsonProducer {

    private final KafkaTemplate<String, OrderEntity> kafkaTemplate;

    public void sendMessage(OrderRequest orderRequest) {

        Message<OrderRequest> message = MessageBuilder
                .withPayload(orderRequest)
                .setHeader(KafkaHeaders.TOPIC, "mini-projet")
                .build();

        kafkaTemplate.send(message);
    }

}
