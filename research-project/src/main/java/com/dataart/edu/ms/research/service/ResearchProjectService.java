package com.dataart.edu.ms.research.service;

import com.dataart.edu.ms.domain.EventType;
import com.dataart.edu.ms.event.processor.EventProcessor;
import com.dataart.edu.ms.research.domain.Phase;
import com.dataart.edu.ms.research.domain.ResearchProject;
import com.dataart.edu.ms.research.domain.Status;
import com.dataart.edu.ms.research.repository.ResearchProjectRepository;
import com.dataart.edu.ms.utils.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchProjectService {
    private final Logger LOG = LoggerFactory.getLogger(ResearchProjectService.class);

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createResearchProject(ResearchProject researchProject) {
        if (StringUtils.isEmpty(researchProject.getId())) {
            researchProject.setId(IDGenerator.nextId());
        }
        researchProjectRepository.save(researchProject);
        eventProcessor.processEntity(EventType.RESEARCH_PROJECT_CREATED.type, "research project created " + researchProject.getId(), researchProject);
        LOG.info("research project created");
    }

    public void updateResearchProject(ResearchProject researchProject) {
        if (StringUtils.isEmpty(researchProject.getId())) {
            return;
        }
        researchProjectRepository.save(researchProject);
        eventProcessor.processEntity(EventType.RESEARCH_PROJECT_UPDATED.type, "research project updated " + researchProject.getId(), researchProject);
        LOG.info("research project updated");
    }

    public Optional<ResearchProject> getByProductId(String productId) {
        return researchProjectRepository.findByProductId(productId);
    }

    public void assignTeam(String transactionId, String teamId) {

    }

    public List<ResearchProject> list() {
        return researchProjectRepository.findAll();
    }

    @Transactional
    public void start(String projectId) {
        Optional<ResearchProject> byId = researchProjectRepository.findById(projectId);
        if (byId.isEmpty()) {
            return;
        }

        ResearchProject researchProject = byId.get();
        Phase phase = researchProject.getPhase();
        Status status = researchProject.getStatus();

        if (status != Status.INITIALIZED || phase != Phase.RESEARCH) {
            return;
        }

        researchProject.setStatus(Status.IN_PROGRESS);
        researchProjectRepository.save(researchProject);
    }

    @Transactional
    public void close(String projectId) {
        Optional<ResearchProject> byId = researchProjectRepository.findById(projectId);
        if (byId.isEmpty()) {
            return;
        }

        ResearchProject researchProject = byId.get();
        Status status = researchProject.getStatus();
        Phase phase = researchProject.getPhase();

        if (status != Status.IN_PROGRESS || phase != Phase.RESEARCH) {
            return;
        }

        researchProject.setStatus(Status.DONE);
        researchProjectRepository.save(researchProject);
    }

    @Transactional
    public void startDevelopment(String projectId) {
        Optional<ResearchProject> byId = researchProjectRepository.findById(projectId);
        if (byId.isEmpty()) {
            return;
        }

        ResearchProject researchProject = byId.get();
        Phase phase = researchProject.getPhase();
        Status status = researchProject.getStatus();

        if (status != Status.DONE || phase != Phase.RESEARCH) {
            return;
        }

        researchProject.setStatus(Status.IN_PROGRESS);
        researchProject.setPhase(Phase.DEVELOPMENT);
        researchProjectRepository.save(researchProject);
    }

    @Transactional
    public void closeDevelopment(String projectId) {
        Optional<ResearchProject> byId = researchProjectRepository.findById(projectId);
        if (byId.isEmpty()) {
            return;
        }

        ResearchProject researchProject = byId.get();
        Status status = researchProject.getStatus();
        Phase phase = researchProject.getPhase();

        if (status != Status.IN_PROGRESS || phase != Phase.DEVELOPMENT) {
            return;
        }

        researchProject.setStatus(Status.DONE);
        researchProjectRepository.save(researchProject);
    }

}
