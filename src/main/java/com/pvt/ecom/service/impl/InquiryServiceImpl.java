package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.InquiryStatus;
import com.pvt.ecom.model.dto.InquiryDTO;
import com.pvt.ecom.model.entity.Inquiry;
import com.pvt.ecom.model.mapper.InquiryMapper;
import com.pvt.ecom.repository.InquiryRepository;
import com.pvt.ecom.repository.PropertyRepository;
import com.pvt.ecom.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
    private final PropertyRepository propertyRepository;
    private final InquiryMapper inquiryMapper;

    @Override
    public List<InquiryDTO> getInquiriesForUser(Long userId, String role, String email) {
        if ("admin".equalsIgnoreCase(role)) {
            return inquiryMapper.toDTOList(inquiryRepository.findAll());
        } else if ("customer".equalsIgnoreCase(role)) {
            return inquiryMapper.toDTOList(inquiryRepository.findByCustomerId(userId));
        } else {
            List<Inquiry> receivedInquiries = inquiryRepository.findByPropertyOwnerId(userId);
            List<Inquiry> sentInquiries = inquiryRepository.findByCustomerId(userId);

            List<Inquiry> allInquiries = Stream.concat(receivedInquiries.stream(), sentInquiries.stream())
                    .distinct() // Ensure no duplicates if an owner inquires on their own property
                    .sorted(Comparator.comparing(Inquiry::getCreatedAt).reversed())
                    .collect(Collectors.toList());

            return inquiryMapper.toDTOList(allInquiries);
        }
    }

    @Override
    public InquiryDTO replyToInquiry(Long inquiryId, String replyMessage) {
        return inquiryRepository.findById(inquiryId).map(inquiry -> {
            inquiry.setReply(replyMessage);
            inquiry.setStatus(InquiryStatus.RESPONDED);
            return inquiryMapper.toDTO(inquiryRepository.save(inquiry));
        }).orElseThrow(() -> new RuntimeException("Inquiry not found"));
    }

    @Override
    public InquiryDTO addInquiry(InquiryDTO inquiryDTO, Long propertyId) {
        Inquiry inquiry = inquiryMapper.toEntity(inquiryDTO);
        return propertyRepository.findById(propertyId).map(property -> {
            inquiry.setProperty(property);
            inquiry.setStatus(InquiryStatus.NEW);
            return inquiryMapper.toDTO(inquiryRepository.save(inquiry));
        }).orElseThrow(() -> new RuntimeException("Property not found with ID: " + propertyId));
    }
}
