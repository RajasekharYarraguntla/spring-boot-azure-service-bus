package com.raja.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {


    @JmsListener(destination = "${azure.servicebus.topics.order}/subscriptions/${azure.servicebus.subscriptions.order}")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
