package com.raja.config.servicebus.consumer;

import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.raja.dto.OrderEvent;
import com.raja.dto.UserEvent;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DynamicProcessorsLifecycle implements SmartLifecycle {

    private final List<ServiceBusProcessorClient> processors = new ArrayList<>();
    private volatile boolean running = false;

    public DynamicProcessorsLifecycle(ServiceBusProcessorFactory factory) {
        processors.add(factory.createProcessor("raja", "user-subscription", UserEvent.class));
        processors.add(factory.createProcessor("raja", "order-subscription", OrderEvent.class));
    }

    @Override
    public void start() {
        processors.forEach(ServiceBusProcessorClient::start);
        running = true;
    }

    @Override
    public void stop() {
        processors.forEach(ServiceBusProcessorClient::close);
        running = false;
    }

    @Override
    public boolean isRunning() { return running; }
    @Override
    public boolean isAutoStartup() { return true; }
    @Override
    public int getPhase() { return Integer.MAX_VALUE; }
}
