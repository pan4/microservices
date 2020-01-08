package com.dataart.edu.ms.business.controller;

import com.dataart.edu.ms.business.domain.BusinessArea;
import com.dataart.edu.ms.business.service.BusinessAreaService;
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
public class BusinessAreaController {
    private final Logger LOG = LoggerFactory.getLogger(BusinessAreaController.class);

    @Autowired
    private BusinessAreaService businessAreaService;

    @PostMapping("/business_area")
    public ResponseEntity<BusinessArea> create(@RequestBody BusinessArea businessArea) {
        this.businessAreaService.createBusiness(businessArea);
        return ResponseEntity.ok(businessArea);
    }

    @GetMapping("/business_area/list")
    public ResponseEntity<List<BusinessArea>> list() {
        return ResponseEntity.ok(this.businessAreaService.list());
    }
}
