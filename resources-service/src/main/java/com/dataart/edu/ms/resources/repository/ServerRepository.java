package com.dataart.edu.ms.resources.repository;

import com.dataart.edu.ms.resources.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, String> {

    Server findFirstByBusy(Boolean busy);

}
