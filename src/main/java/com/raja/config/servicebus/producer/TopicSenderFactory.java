package com.raja.config.servicebus.producer;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class TopicSenderFactory {

    private final ServiceBusClientBuilder builder;
    private final Map<String, ServiceBusSenderClient> senderCache = new ConcurrentHashMap<>();

    public TopicSenderFactory(ServiceBusClientBuilder builder) {
        this.builder = builder;
    }

    public ServiceBusSenderClient getSender(String topicName) {
        return senderCache.computeIfAbsent(topicName, topic -> builder.sender().topicName(topic).buildClient());
    }

    @PreDestroy
    public void closeAll() {
        log.info("Closing all ServiceBusSenderClients{}", senderCache);
        senderCache.values().forEach(ServiceBusSenderClient::close);
    }
}
