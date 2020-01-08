package com.dataart.edu.ms.research.controller;

import com.dataart.edu.ms.research.domain.ResearchProject;
import com.dataart.edu.ms.research.service.ResearchProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResearchProjectController {
    private static final Logger LOG = LoggerFactory.getLogger(ResearchProjectController.class);

    @Autowired
    private ResearchProjectService researchProjectService;

    @PostMapping("/research")
    public ResponseEntity<ResearchProject> create(@RequestBody ResearchProject researchProject) {
        this.researchProjectService.createResearchProject(researchProject);
        return ResponseEntity.ok(researchProject);
    }

    @GetMapping("/research/list")
    public ResponseEntity<List<ResearchProject>> list() {
        return ResponseEntity.ok(this.researchProjectService.list());
    }

    @PostMapping("/research/{projectId}/start")
    public void startProject(@PathVariable("projectId") String projectId) {
        this.researchProjectService.start(projectId);
    }

    @PostMapping("/research/{projectId}/close")
    public void stopProject(@PathVariable("projectId") String projectId) {
        this.researchProjectService.close(projectId);
    }

    @PostMapping("/development/{projectId}/start")
    public void startProjectDevelopment(@PathVariable("projectId") String projectId) {
        this.researchProjectService.startDevelopment(projectId);
    }

    @PostMapping("/development/{projectId}/close")
    public void closeProjectDevelopment(@PathVariable("projectId") String projectId) {
        this.researchProjectService.closeDevelopment(projectId);
    }
}
