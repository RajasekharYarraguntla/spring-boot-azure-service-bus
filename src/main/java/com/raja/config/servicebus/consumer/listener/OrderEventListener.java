package com.raja.config.servicebus.consumer.listener;

import com.raja.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventListener implements EventListener<OrderEvent> {
    @Override
    public void consume(OrderEvent event) {
        log.info("Consuming OrderEvent: {}", event);
    }
}