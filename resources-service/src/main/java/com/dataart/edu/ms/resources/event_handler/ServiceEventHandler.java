package com.dataart.edu.ms.resources.event_handler;

import com.dataart.edu.ms.resources.domain.Team;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.domain.TransactionType;
import com.dataart.edu.ms.resources.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventHandler implements EventHandler {
    private final Logger LOG = LoggerFactory.getLogger(ServiceEventHandler.class);

    @Autowired
    private EventProcessor eventProcessor;

    @Autowired
    private TeamService teamService;

    @Override
    public void handle(Event event) {
        LOG.info("Handle event: {}", event.getId());
        if (EventType.RESEARCH_PROJECT_STARTED.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            Team freeTeam = teamService.findFreeTeam();
            if (freeTeam != null) {
                eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                        EventType.TEAM_FOUND.type, "found team " + freeTeam.getId(), freeTeam.getId());
            } else {
                eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                        EventType.RESEARCH_PROJECT_REVERT_START.type, "Mark research project as not started", "");
            }
        }
    }

}
