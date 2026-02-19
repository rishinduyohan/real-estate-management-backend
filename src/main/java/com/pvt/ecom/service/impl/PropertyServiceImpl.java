package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.entity.Property;
import com.pvt.ecom.repository.PropertyRepository;
import com.pvt.ecom.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public boolean deleteProperty(Long id) {
        propertyRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Property> getPropertiesByOwner(Long ownerId) {
        return propertyRepository.getPropertyByOwnerId(ownerId);
    }
}
