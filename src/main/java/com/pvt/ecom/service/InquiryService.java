package com.pvt.ecom.service;

import com.pvt.ecom.model.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {
    List<InquiryDTO> getInquiriesForUser(Long userId, String role, String email);

    InquiryDTO replyToInquiry(Long inquiryId, String replyMessage);

    InquiryDTO addInquiry(InquiryDTO inquiry, Long propertyId);
}
