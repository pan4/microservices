package com.dataart.edu.ms.product.event_handler;

import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.domain.TransactionType;
import com.dataart.edu.ms.event.domain.Event;
import com.dataart.edu.ms.event.handler.EventHandler;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.product.domain.Product;
import com.dataart.edu.ms.product.repository.ProductRepository;
import com.dataart.edu.ms.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Component
public class ServiceEventHandler implements EventHandler {
    private final Logger LOG = LoggerFactory.getLogger(ServiceEventHandler.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private EventProcessor eventProcessor;

    @Override
    public void handle(Event event) {
        LOG.info("Handle event: {}", event.getId());
        if (EventType.TEAM_ASSIGNED_TO_RESEARCH_PROJECT.type.equals(event.getType()) &&
                TransactionType.ESTABLISH_PRODUCT.type.equals(event.getTransactionType())) {
            Map<String, Object> payload = event.getPayloadAsMap();
            String productId = payload.get("productId").toString();
            String prototypeId = payload.get("prototypeId").toString();
            String teamId = payload.get("teamId").toString();
            Optional<Product> productOptional = productService.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setPrototypesIds(Collections.singletonList(prototypeId));
                product.setTeamId(teamId);
                productService.updateProduct(product);
                eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                        EventType.TEAM_ASSIGNED_TO_PRODUCT.type, "team assigned to product " + productId, payload);
            } else {
                eventProcessor.processTransactionalText(TransactionType.ESTABLISH_PRODUCT.type, event.getTransactionId(),
                        EventType.TEAM_ASSIGN_TO_RESEARCH_PROJECT_ROLLBACK.type, "team assign to research project rollback " + productId, payload);
            }
        }
    }

}
