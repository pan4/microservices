package com.dataart.edu.ms.resources.controller;

import com.dataart.edu.ms.resources.domain.Team;
import com.dataart.edu.ms.resources.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {
    private static final Logger LOG = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> create(@RequestBody Team team) {
        this.teamService.createTeam(team);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/team/list")
    public ResponseEntity<List<Team>> list() {
        return ResponseEntity.ok(this.teamService.list());
    }
}
