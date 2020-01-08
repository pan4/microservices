package com.dataart.edu.ms.research.repository;

import com.dataart.edu.ms.research.domain.ResearchProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchProjectRepository extends JpaRepository<ResearchProject, String> {

    ResearchProject findByProductId(String productId);

}