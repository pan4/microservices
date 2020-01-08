package com.dataart.edu.ms.client.controller;

import com.dataart.edu.ms.client.domain.Client;
import com.dataart.edu.ms.client.service.ClientService;
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
public class ClientController {

    private final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        this.clientService.createClient(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/client/list")
    public ResponseEntity<List<Client>> list() {
        return ResponseEntity.ok(this.clientService.list());
    }
}
