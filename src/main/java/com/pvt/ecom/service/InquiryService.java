package com.pvt.ecom.service;

import com.pvt.ecom.model.entity.Inquiry;

import java.util.List;

public interface InquiryService {
    List<Inquiry> getInquiriesForUser(Long userId, String role, String email);

    Inquiry replyToInquiry(Long inquiryId, String replyMessage);
}
