package com.raja.config.servicebus.consumer;

import com.raja.config.servicebus.consumer.listener.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConsumerRegistry {

    private final Map<String, EventListener<?>> registry = new ConcurrentHashMap<>();

    public <T> void register(String topic, String subscription, EventListener<T> consumer) {
        String key = topic + ":" + subscription;
        registry.put(key, consumer);
    }

    public EventListener<?> get(String topic, String subscription) {
        return registry.get(topic + ":" + subscription);
    }
}
