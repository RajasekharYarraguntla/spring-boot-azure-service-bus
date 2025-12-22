package com.raja.config.servicebus.consumer.listener;

public interface EventListener<T> {
    void consume(T event);
}
