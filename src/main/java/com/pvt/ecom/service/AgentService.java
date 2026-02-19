package com.pvt.ecom.service;

import com.pvt.ecom.model.entity.Agent;

import java.util.List;

public interface AgentService {
     List<Agent> getAllAgents();

     List<Agent> getAgentsByOwner(Long ownerId);

     Agent approveAgentRequest(Long userId, String location);
}
