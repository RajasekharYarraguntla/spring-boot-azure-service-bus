package com.raja.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @JmsListener(destination = "${azure.servicebus.topics.payment}/subscriptions/${azure.servicebus.subscriptions.payment}")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
