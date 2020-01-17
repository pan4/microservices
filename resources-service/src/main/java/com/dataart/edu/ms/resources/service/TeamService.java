package com.dataart.edu.ms.resources.service;

import com.dataart.edu.ms.resources.domain.Team;
import com.dataart.edu.ms.resources.repository.TeamRepository;
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
public class TeamService {
    private final Logger LOG = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createTeam(Team team) {
        if (StringUtils.isEmpty(team.getId())) {
            team.setId(IDGenerator.nextId());
        }
        teamRepository.save(team);
        eventProcessor.processEntity(EventType.TEAM_ADDED.type, "team added " + team.getId(), team);
        LOG.info("server {} created", team.getId());
    }

    public Team findFreeTeam() {
        return teamRepository.findByBusy(false);
    }

    public Optional<Team> findTeamById(String teamId) {
        return teamRepository.findById(teamId);
    }

    public List<Team> list() {
        return this.teamRepository.findAll();
    }

    public void updateTeam(Team team){
        if (StringUtils.isEmpty(team.getId())) {
            return;
        }
        teamRepository.save(team);
    }
}
