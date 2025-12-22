package com.raja.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ShipmentListener {

    @JmsListener(destination = "${azure.servicebus.topics.shipment}/subscriptions/${azure.servicebus.subscriptions.shipment}")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
