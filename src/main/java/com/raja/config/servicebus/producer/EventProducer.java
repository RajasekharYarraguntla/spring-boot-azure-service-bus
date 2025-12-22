package com.raja.config.servicebus.producer;

public interface EventProducer {
    void send(String topic, Object payload);
}
