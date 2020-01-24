package com.dataart.edu.ms.research.event_handler;

import com.dataart.edu.ms.research.domain.ResearchProject;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.research.domain.Status;
import com.dataart.edu.ms.research.service.ResearchProjectService;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.domain.TransactionType;
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
    private static final Logger LOG = LoggerFactory.getLogger(ServiceEventHandler.class);

    @Autowired
    private ResearchProjectService researchProjectService;

    @Autowired
    private EventProcessor eventProcessor;

    @Override
    public void handle(Event event) {
        LOG.info("Handle event: {}", event.getId());
        if (EventType.PRODUCT_ESTABLISH_STARTED.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleProjectEstablishStartedEvent(event);
        } else if (EventType.TEAM_FOUND.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleTeamFoundEvent(event);
        } else if (EventType.RESEARCH_PROJECT_REVERT_START.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleResearchProjectRevertStartEvent(event);
        } else if (EventType.TEAM_ASSIGN_TO_RESEARCH_PROJECT_ROLLBACK.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            handleTeamAssignToResearchProjectRollbackEvent(event);
        }
    }

    private void handleTeamAssignToResearchProjectRollbackEvent(Event event) {
        handleResearchProjectRevertStartEvent(event);
        Map<String, Object> payload = event.getPayloadAsMap();
        String teamId = payload.get("teamId").toString();
        eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                EventType.TEAM_ADD_ROLLBACK.type, "team add rollback " + teamId, payload);
    }

    private void handleResearchProjectRevertStartEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        if (StringUtils.isEmpty(payload.get("productId"))) {
            return;
        }
        String productId = payload.get("productId").toString();
        Optional<ResearchProject> researchProjectOptional = researchProjectService.getByProductId(productId);
        researchProjectOptional.ifPresent(researchProject -> {
            researchProject.setStatus(Status.INITIALIZED);
            researchProject.setTeamId(null);
            researchProjectService.updateResearchProject(researchProject);
        });
    }

    private void handleTeamFoundEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        String productId = payload.get("productId").toString();
        Optional<ResearchProject> researchProjectOptional = researchProjectService.getByProductId(productId);
        researchProjectOptional.ifPresent(researchProject -> {
            String teamId = payload.get("teamId").toString();
            researchProject.setTeamId(teamId);

            String serverId = payload.get("serverId").toString();
            researchProject.setServerId(serverId);

            researchProjectService.updateResearchProject(researchProject);
            payload.put("prototypeId", researchProject.getId());
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.TEAM_ASSIGNED_TO_RESEARCH_PROJECT.type, "team assigned to research project", payload);
        });
    }

    private void handleProjectEstablishStartedEvent(Event event) {
        Map<String, Object> payload = event.getPayloadAsMap();
        String productId = payload.get("productId").toString();
        Optional<ResearchProject> researchProjectOptional = researchProjectService.getByProductId(productId);
        if (researchProjectOptional.isPresent()) {
            ResearchProject researchProject = researchProjectOptional.get();
            researchProject.setStatus(Status.IN_PROGRESS);
            researchProjectService.updateResearchProject(researchProject);
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.RESEARCH_PROJECT_STARTED.type, "start research project", payload);
        } else {
            LOG.error("Can not handle PRODUCT_ESTABLISH_STARTED event. Product is not found");
        }
    }

}
