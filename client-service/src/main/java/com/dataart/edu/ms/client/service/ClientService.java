package com.dataart.edu.ms.client.service;

import com.dataart.edu.ms.client.repository.ClientRepository;
import com.dataart.edu.ms.client.domain.Client;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.utils.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClientService {

    private final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createClient(Client client) {
        if (StringUtils.isEmpty(client.getId())) {
            client.setId(IDGenerator.nextId());
        }
        clientRepository.save(client);
        eventProcessor.processEntity(EventType.CLIENT_CREATED.type, "client created " + client.getId(), client);
        LOG.error("client created");
    }

    public List<Client> list() {
        return this.clientRepository.findAll();
    }
}
