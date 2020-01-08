package com.dataart.edu.ms.business.repository;

import com.dataart.edu.ms.business.domain.BusinessArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessAreaRepository extends JpaRepository<BusinessArea, String> {
}
