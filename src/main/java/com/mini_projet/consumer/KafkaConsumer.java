package com.mini_projet.consumer;


import com.mini_projet.entities.dto.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor

public class KafkaConsumer {



    // @KafkaListener(topics = "mini-projet", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info(format("Consuming the message from mini-projet Topic:: %s", msg));
    }

    @KafkaListener(topics = "mini-projet", groupId = "myGroup")
    public void consumeJsonMsg(OrderRequest orderRequest) {
        log.info(format("Consuming the message from mini-projet Topic:: %s", orderRequest.toString()));
    }

}
