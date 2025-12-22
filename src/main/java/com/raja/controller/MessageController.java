package com.raja.controller;

import com.raja.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class MessageController {

    private final MessageProducer producer;

    @Value("${azure.servicebus.topics.order}")
    private String orderTopic;

    @Value("${azure.servicebus.topics.payment}")
    private String paymentTopic;

    @Value("${azure.servicebus.topics.shipment}")
    private String shipmentTopic;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/{type}")
    public String publish(@PathVariable String type, @RequestBody String message) {
        System.out.println("Publishing message to " + type + " topic: " + message);
        switch (type) {
            case "order" -> producer.send(orderTopic, message);
            case "payment" -> producer.send(paymentTopic, message);
            case "shipment" -> producer.send(shipmentTopic, message);
            default -> throw new IllegalArgumentException("Invalid topic type");
        }
        return "Message sent to " + type;
    }
}
