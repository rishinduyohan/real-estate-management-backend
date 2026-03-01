package com.pvt.ecom.model.mapper;

import com.pvt.ecom.model.dto.InquiryDTO;
import com.pvt.ecom.model.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InquiryMapper {

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "property.title", target = "propertyTitle")
    @Mapping(source = "createdAt", target = "createdAt")
    InquiryDTO toDTO(Inquiry inquiry);

    @Mapping(source = "propertyId", target = "property.id")
    @Mapping(target = "repliedAt", ignore = true)
    Inquiry toEntity(InquiryDTO inquiryDTO);

    List<InquiryDTO> toDTOList(List<Inquiry> inquiries);
}
