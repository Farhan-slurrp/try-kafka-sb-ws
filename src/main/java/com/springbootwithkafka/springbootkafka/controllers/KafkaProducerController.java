package com.springbootwithkafka.springbootkafka.controllers;

package com.springbootwithkafka.springbootkafka.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaProducerService<String> producerService;


    @GetMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
        producerService.sendMessage(message);
        return "Message sent.";
    }
}