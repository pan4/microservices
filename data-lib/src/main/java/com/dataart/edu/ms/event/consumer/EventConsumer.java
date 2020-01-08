package com.dataart.edu.ms.event.consumer;

import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.event.processor.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private EventProcessor eventProcessor;

    @Autowired
    private EventHandler eventHandler;

    @RabbitListener(queues = {"${app.rabbit.active_queue}"})
    public void receivedMessage(String message) {
        LOG.info("Received message: " + message);
        System.out.println("Received message: " + message);
        Event event = eventProcessor.extractObject(message);
        System.out.println("Payload: " + event.getPayload().toString());
        eventHandler.handle(event);
    }

}
