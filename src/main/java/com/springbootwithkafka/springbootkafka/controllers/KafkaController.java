package com.springbootwithkafka.springbootkafka.controllers;

package com.springbootwithkafka.springbootkafka.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService<String> producerService;


    @GetMapping(value = "api/v1/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
        producerService.sendMessage(message);
        return "Message sent.";
    }

    //    -------------- WebSocket API ----------------
    @MessageMapping("/sendMessage")
    @SendTo("/poems/consumer_1")
    public Message broadcastGroupMessage(@Payload String message) {
        //Sending this message to all the subscribers
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/poems/consumer_1")
    public Message addUser(@Payload String message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}