package com.raja.config.servicebus.consumer;

import com.raja.config.servicebus.consumer.listener.OrderEventListener;
import com.raja.config.servicebus.consumer.listener.UserEventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRegistration {

    public ConsumerRegistration(ConsumerRegistry registry) {
        registry.register("raja", "user-subscription", new UserEventListener());
        registry.register("raja", "order-subscription", new OrderEventListener());
    }
}
