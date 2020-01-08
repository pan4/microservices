package com.dataart.edu.ms.resources.controller;

import com.dataart.edu.ms.resources.domain.Server;
import com.dataart.edu.ms.resources.service.ServerService;
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
public class ServerController {
    private static final Logger LOG = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private ServerService serverService;

    @PostMapping("/server")
    public ResponseEntity<Server> create(@RequestBody Server server) {
        this.serverService.createServer(server);
        return ResponseEntity.ok(server);
    }

    @GetMapping("/server/list")
    public ResponseEntity<List<Server>> list() {
        return ResponseEntity.ok(this.serverService.list());
    }
}
