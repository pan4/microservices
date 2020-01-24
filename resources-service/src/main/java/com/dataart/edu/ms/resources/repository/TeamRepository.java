package com.dataart.edu.ms.resources.repository;

import com.dataart.edu.ms.resources.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    Team findFirstByBusy(Boolean busy);

}
