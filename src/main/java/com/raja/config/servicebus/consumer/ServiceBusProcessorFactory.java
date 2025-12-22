package com.raja.config.servicebus.consumer;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raja.config.servicebus.consumer.listener.EventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceBusProcessorFactory {

    private final ServiceBusClientBuilder builder;
    private final ObjectMapper objectMapper;
    private final ConsumerRegistry consumerRegistry;

    public ServiceBusProcessorFactory(ServiceBusClientBuilder builder, ObjectMapper objectMapper,
                                      ConsumerRegistry consumerRegistry) {
        this.builder = builder;
        this.objectMapper = objectMapper;
        this.consumerRegistry = consumerRegistry;
    }

    public ServiceBusProcessorClient createProcessor(String topic, String subscription, Class<?> eventType) {

        ServiceBusClientBuilder.ServiceBusProcessorClientBuilder build = builder.processor().topicName(topic)
                .subscriptionName(subscription).disableAutoComplete();
        build.processMessage(context -> {

                    try {
                        String body = context.getMessage().getBody().toString();
                        Object event = objectMapper.readValue(body, eventType);
                        EventListener consumer = consumerRegistry.get(topic, subscription);
                        if (consumer != null) {
                            consumer.consume(event);
                        } else {
                            log.info(" No consumer registered for {}:{}", topic, subscription);
                        }
                        context.complete();
                        log.info("Message processed successfully");
                    } catch (Exception e) {
                        context.abandon();
                    }
                })
                .processError(errorContext ->
                        log.error("Error: {}", String.valueOf(errorContext.getException())));
        return build.buildProcessorClient();
    }
}
