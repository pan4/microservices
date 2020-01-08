package com.dataart.edu.ms.research.event_handler;

import com.dataart.edu.ms.research.domain.ResearchProject;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.research.domain.Status;
import com.dataart.edu.ms.research.service.ResearchProjectService;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.domain.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            String productId = event.getPayload();
            ResearchProject researchProject = researchProjectService.getByProductId(productId);
            researchProject.setStatus(Status.IN_PROGRESS);
            researchProjectService.updateResearchProject(researchProject);
            eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                    EventType.RESEARCH_PROJECT_STARTED.type, "start research project", productId);
        } else if (EventType.TEAM_FOUND.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {

        }
    }

}
