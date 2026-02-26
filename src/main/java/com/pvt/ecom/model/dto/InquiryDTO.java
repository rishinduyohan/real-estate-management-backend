package com.pvt.ecom.model.dto;

import com.pvt.ecom.model.InquiryStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class InquiryDTO {
    private Long id;
    private Long propertyId;
    private Long customerId;
    private String propertyTitle;
    private String name;
    private String email;
    private String phone;
    private String message;
    private InquiryStatus status;
    private String reply;
    private LocalDateTime createdAt;
}
