package com.raja.controller;


import com.raja.config.servicebus.producer.ServiceBusProducer;
import com.raja.dto.OrderEvent;
import com.raja.dto.UserEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final ServiceBusProducer producer;

    @Value("${azure.servicebus.topic-name}")
    private String topicName;

    public MessageController(ServiceBusProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/users")
    public String sendUser(@Validated @RequestBody UserEvent message) {
        producer.send(topicName, message);
        return "Message sent to topic";
    }

    @PostMapping("/orders")
    public String sendOrder(@Validated @RequestBody OrderEvent message) {
        producer.send(topicName, message);
        return "Message sent to topic";
    }
}
