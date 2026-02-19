package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.entity.Agent;
import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.repository.AgentRepository;
import com.pvt.ecom.repository.UserRepository;
import com.pvt.ecom.service.AgentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public List<Agent> getAgentsByOwner(Long ownerId) {
        return agentRepository.findByOwnerId(ownerId);
    }

    @Override
    @Transactional
    public Agent approveAgentRequest(Long userId, String location) {
        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Agent agent = new Agent();
        agent.setUser(user);
        agent.setLocation(location);
        agent.setListingsCount(0);
        agent.setSoldCount(0);
        return agentRepository.save(agent);
    }
}
