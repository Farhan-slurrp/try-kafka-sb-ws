package com.springbootwithkafka.springbootkafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "poems", containerFactory = "kafkaListenerStringFactory", groupId = "consumer_1")
    public void consumeMessage(String message) {
        logger.info("**** -> Consumed message -> {}", message);
        template.convertAndSend("/poems/consumer_1", message);
    }
}