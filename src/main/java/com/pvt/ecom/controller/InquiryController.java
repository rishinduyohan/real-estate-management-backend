package com.pvt.ecom.controller;

import com.pvt.ecom.model.entity.Inquiry;
import com.pvt.ecom.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Inquiry>> getMyInquiries(
            @PathVariable Long userId,
            @RequestParam String role,
            @RequestParam String email) {
        return ResponseEntity.ok(inquiryService.getInquiriesForUser(userId, role, email));
    }

    @PostMapping("/reply/{id}")
    public ResponseEntity<Inquiry> replyToInquiry(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        String replyMessage = request.get("reply");
        return ResponseEntity.ok(inquiryService.replyToInquiry(id, replyMessage));
    }
}
