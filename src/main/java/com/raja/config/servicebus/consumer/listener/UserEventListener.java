package com.raja.config.servicebus.consumer.listener;

import com.raja.dto.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventListener implements EventListener<UserEvent> {
    @Override
    public void consume(UserEvent event) {
        log.info("Consuming User Event: {}", event);
    }
}


