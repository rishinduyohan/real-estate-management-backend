package com.pvt.ecom.service;

import com.pvt.ecom.model.dto.PropertyDTO;
import com.pvt.ecom.model.entity.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
     List<Property> getAllProperties();

     PropertyDTO getPropertyById(Long id);

     Property addProperty(Property property);

     boolean deleteProperty(Long id);

     List<Property> getPropertiesByOwner(Long ownerId);

    Property updateProperty(Property property);
}
