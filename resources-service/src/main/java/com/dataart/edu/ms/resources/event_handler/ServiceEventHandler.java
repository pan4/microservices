package com.dataart.edu.ms.resources.event_handler;

import com.dataart.edu.ms.resources.domain.Team;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.domain.TransactionType;
import com.dataart.edu.ms.resources.service.ServerService;
import com.dataart.edu.ms.resources.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

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
            handleResearchProjectStartEvent(event);
        } else if (EventType.TEAM_ADD_ROLLBACK.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleTeamAddRollbackEvent(event);
        }

    }

    private void handleTeamAddRollbackEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        if (StringUtils.isEmpty(payload.get("teamId"))) {
            return;
        }
        String teamId = payload.get("teamId").toString();
        Optional<Team> teamOptional = teamService.findTeamById(teamId);
        teamOptional.ifPresent(team -> {
            team.setBusy(false);
            teamService.updateTeam(team);
        });
    }

    private void handleResearchProjectStartEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        Team freeTeam = teamService.findFreeTeam();
        if (freeTeam != null) {
            freeTeam.setBusy(true);
            teamService.updateTeam(freeTeam);
            payload.put("teamId", freeTeam.getId());
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.TEAM_FOUND.type, "found team " + freeTeam.getId(), payload);
        } else {
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.RESEARCH_PROJECT_REVERT_START.type, "Mark research project as not started", payload);
        }
    }

}
