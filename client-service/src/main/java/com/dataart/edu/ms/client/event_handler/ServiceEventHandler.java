package com.dataart.edu.ms.client.event_handler;

import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventHandler implements EventHandler {
    private final Logger LOG = LoggerFactory.getLogger(ServiceEventHandler.class);

    @Override
    public void handle(Event event) {
        LOG.info("Handle event: {}", event.getId());
    }

}
