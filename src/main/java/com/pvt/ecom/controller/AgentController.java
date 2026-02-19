package com.pvt.ecom.controller;

import com.pvt.ecom.model.entity.Agent;
import com.pvt.ecom.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AgentController {
    private final AgentService agentService;

    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Agent>> getAgentsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(agentService.getAgentsByOwner(ownerId));
    }

    @PostMapping("/approve/{userId}")
    public ResponseEntity<Agent> approveAgent(
            @PathVariable Long userId,
            @RequestParam String location) {
        return ResponseEntity.ok(agentService.approveAgentRequest(userId, location));
    }
}
