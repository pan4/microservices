package com.dataart.edu.ms.resources.event_handler;

import com.dataart.edu.ms.resources.domain.Server;
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

    @Autowired
    private ServerService serverService;

    @Override
    public void handle(Event event) {
        LOG.info("Handle event: {}", event.getId());
        if (EventType.RESEARCH_PROJECT_STARTED.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleResearchProjectStartEvent(event);
        } else if (EventType.TEAM_ADD_ROLLBACK.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleTeamAddRollbackEvent(event);
        } else if (EventType.PRODUCT_RELEASE_STARTED.type.equals(event.getType()) &&
                TransactionType.RELEASE_PRODUCT.type.equals(event.getTransactionType())) {
            handleReleaseProductEvent(event);
        }

    }

    private void handleReleaseProductEvent(Event event) {
        handleTeamAddRollbackEvent(event);
    }

    private void handleTeamAddRollbackEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        if (!StringUtils.isEmpty(payload.get("teamId"))) {
            String teamId = payload.get("teamId").toString();
            Optional<Team> teamOptional = teamService.findTeamById(teamId);
            teamOptional.ifPresent(team -> {
                team.setBusy(false);
                teamService.updateTeam(team);
            });
        }

        if (!StringUtils.isEmpty(payload.get("serverId"))) {
            String serverId = payload.get("serverId").toString();
            Optional<Server> serverOptional = serverService.findServerById(serverId);
            serverOptional.ifPresent(server -> {
                server.setBusy(false);
                serverService.updateServer(server);
            });
        }
    }

    private void handleResearchProjectStartEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        Team freeTeam = teamService.findFreeTeam();
        Server freeServer = serverService.findFreeServer();
        if (freeTeam != null && freeServer != null) {
            freeTeam.setBusy(true);
            teamService.updateTeam(freeTeam);
            payload.put("teamId", freeTeam.getId());

            freeServer.setBusy(true);
            serverService.updateServer(freeServer);
            payload.put("serverId", freeServer.getId());

            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.TEAM_FOUND.type, "found team " + freeTeam.getId(), payload);
        } else {
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.RESEARCH_PROJECT_REVERT_START.type, "Mark research project as not started", payload);
            LOG.info("There is not free team or server to assign to the project for the product with id = " + payload.get("productId"));
        }
    }

}
