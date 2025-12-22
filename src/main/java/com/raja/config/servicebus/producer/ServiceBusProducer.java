package com.raja.config.servicebus.producer;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceBusProducer implements EventProducer {

    private final TopicSenderFactory senderFactory;
    private final ObjectMapper objectMapper;

    public ServiceBusProducer(TopicSenderFactory senderFactory, ObjectMapper objectMapper) {
        this.senderFactory = senderFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(String topic, Object payload) {
        try {
            String json = objectMapper.writeValueAsString(payload);
            ServiceBusMessage message = new ServiceBusMessage(json).setContentType("application/json");
            message.getApplicationProperties().put("eventType", payload.getClass().getSimpleName());
            senderFactory.getSender(topic).sendMessage(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }
}
