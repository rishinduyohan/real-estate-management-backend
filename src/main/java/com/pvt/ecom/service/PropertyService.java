package com.pvt.ecom.service;

import com.pvt.ecom.model.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
     List<PropertyDTO> getAllProperties();

     PropertyDTO getPropertyById(Long id);

     PropertyDTO addProperty(PropertyDTO property);

     boolean deleteProperty(Long id);

     List<PropertyDTO> getPropertiesByOwner(Long ownerId);

    PropertyDTO updateProperty(PropertyDTO property);
}
