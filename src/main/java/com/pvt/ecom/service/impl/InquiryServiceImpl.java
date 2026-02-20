package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.InquiryStatus;
import com.pvt.ecom.model.entity.Inquiry;
import com.pvt.ecom.repository.InquiryRepository;
import com.pvt.ecom.repository.PropertyRepository;
import com.pvt.ecom.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;

    @Override
    public List<Inquiry> getInquiriesForUser(Long userId, String role, String email) {
        if ("admin".equalsIgnoreCase(role)) {
            return inquiryRepository.findAll();
        } else if ("customer".equalsIgnoreCase(role)) {
            return inquiryRepository.findByEmail(email);
        } else {
            return inquiryRepository.findByPropertyOwnerId(userId);
        }
    }

    @Override
    public Inquiry replyToInquiry(Long inquiryId, String replyMessage) {
        return inquiryRepository.findById(inquiryId).map(inquiry -> {
            inquiry.setReply(replyMessage);
            inquiry.setStatus(InquiryStatus.RESPONDED);
            return inquiryRepository.save(inquiry);
        }).orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }
}
