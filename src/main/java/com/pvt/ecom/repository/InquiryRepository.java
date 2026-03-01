package com.pvt.ecom.repository;

import com.pvt.ecom.model.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByPropertyId(Long propertyId);

    List<Inquiry> findByCustomerId(Long customerId);

    List<Inquiry> findByPropertyOwnerId(Long ownerId);
}
