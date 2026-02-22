package com.pvt.ecom.model.mapper;

import com.pvt.ecom.model.dto.PropertyDTO;
import com.pvt.ecom.model.entity.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.username", target = "ownerName")
    PropertyDTO toDTO(Property property);

    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "ownerName", target = "owner.username")
    Property toEntity(PropertyDTO propertyDTO);

    List<PropertyDTO> toDTOList(List<Property> properties);
}
