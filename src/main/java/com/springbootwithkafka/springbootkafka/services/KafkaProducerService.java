package com.springbootwithkafka.springbootkafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService<T> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("poems")
    private String topic;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("#### -> Publishing message -> {}", message);
        kafkaTemplate.send(topic, message);
    }
}