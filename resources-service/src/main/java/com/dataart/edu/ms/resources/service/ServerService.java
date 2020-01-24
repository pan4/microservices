package com.dataart.edu.ms.resources.service;

import com.dataart.edu.ms.resources.domain.Server;
import com.dataart.edu.ms.resources.repository.ServerRepository;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.utils.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ServerService {
    private final Logger LOG = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createServer(Server server) {
        if (StringUtils.isEmpty(server.getId())) {
            server.setId(IDGenerator.nextId());
        }
        serverRepository.save(server);
        eventProcessor.processEntity(EventType.SERVER_ADDED.type, "server added " + server.getId(), server);
        LOG.info("server {} created", server.getId());
    }

    public List<Server> list() {
        return this.serverRepository.findAll();
    }

    public Server findFreeServer() {
        return serverRepository.findFirstByBusy(false);
    }

    public void updateServer(Server server){
        if (StringUtils.isEmpty(server.getId())) {
            return;
        }
        serverRepository.save(server);
    }

    public Optional<Server> findServerById(String serverId) {
        return serverRepository.findById(serverId);
    }
}
