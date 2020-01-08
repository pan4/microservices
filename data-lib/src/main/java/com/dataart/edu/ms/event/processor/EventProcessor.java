package com.dataart.edu.ms.event.processor;

import com.dataart.edu.ms.domain.AppEntity;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.repository.EventRepository;
import com.dataart.edu.ms.utils.IDGenerator;
import com.dataart.edu.ms.domain.TransactionType;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor {
    private final Logger LOG = LoggerFactory.getLogger(EventProcessor.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private EventRepository eventRepository;

    private Gson gson = new Gson();

    @Value("${app.rabbit.exchange_name}")
    private String exchangeName;

    @Value("${app.rabbit.active_queue}")
    private String queueName;

    public <T extends AppEntity> void processEntity(String eventType, String eventName, T payload) {
        String payloadAsJson = gson.toJson(payload);
        Event event = new Event(IDGenerator.nextId(), eventName, eventType, payloadAsJson, payload.getClass().getName(),
                payload.getVersion(), TransactionType.DISABLED.type, "");
        String eventJson = gson.toJson(event);
        amqpTemplate.convertAndSend(exchangeName, "", eventJson);
        eventRepository.save(event);
    }

    public void processText(String eventType, String eventName, String text) {
        Event event = new Event(IDGenerator.nextId(), eventName, eventType, text, String.class.getName(),
                0L, TransactionType.DISABLED.type, "");
        String eventJson = gson.toJson(event);
        amqpTemplate.convertAndSend(exchangeName, "", eventJson);
        eventRepository.save(event);
    }

    public <T extends AppEntity> void processTransactionalEntity(String transactionType, String transactionId, String eventType, String eventName, T payload) {
        String payloadAsJson = gson.toJson(payload);
        Event event = new Event(IDGenerator.nextId(), eventName, eventType, payloadAsJson, payload.getClass().getName(),
                payload.getVersion(), transactionType, transactionId);
        String eventJson = gson.toJson(event);
        amqpTemplate.convertAndSend(exchangeName, "", eventJson);
        eventRepository.save(event);
    }

    public <T> void processTransactionalText(String transactionType, String transactionId, String eventType, String eventName, T payload) {
        String payloadAsJson = gson.toJson(payload);
        Event event = new Event(IDGenerator.nextId(), eventName, eventType, payloadAsJson, payload.getClass().getName(),
                0L, transactionType, transactionId);
        String eventJson = gson.toJson(event);
        amqpTemplate.convertAndSend(exchangeName, "", eventJson);
        eventRepository.save(event);
    }

    public Event extractObject(String json) {
        Event event = gson.fromJson(json, Event.class);
        return event;
    }

    public <T> T extractPayload(String payload, Class<T> payloadType) {
        return gson.fromJson(payload, payloadType);
    }

}
