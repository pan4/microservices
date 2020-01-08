package com.dataart.edu.ms.business.service;

import com.dataart.edu.ms.business.domain.BusinessArea;
import com.dataart.edu.ms.business.repository.BusinessAreaRepository;
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
public class BusinessAreaService {

    private final Logger LOG = LoggerFactory.getLogger(BusinessAreaService.class);

    @Autowired
    private BusinessAreaRepository businessAreaRepository;

    @Autowired
    private EventProcessor eventProcessor;

    public void createBusiness(BusinessArea businessArea) {
        if (StringUtils.isEmpty(businessArea.getId())) {
            businessArea.setId(IDGenerator.nextId());
        }
        businessAreaRepository.save(businessArea);
        eventProcessor.processEntity(EventType.BUSINESS_AREA_CREATED.type, "businessArea created " + businessArea.getId(), businessArea);
        LOG.error("businessArea created");
    }

    public List<BusinessArea> list() {
        return businessAreaRepository.findAll();
    }
}
