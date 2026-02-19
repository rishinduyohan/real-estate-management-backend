package com.pvt.ecom.repository;

import com.pvt.ecom.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findByOwnerId(Long ownerId);

    Optional<Agent> findByUserEmail(String email);
}
