package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.Role;
import com.pvt.ecom.model.entity.Property;
import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.repository.PropertyRepository;
import com.pvt.ecom.repository.UserRepository;
import com.pvt.ecom.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

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
        if (property.getOwner() == null || property.getOwner().getId() == null) {
            throw new RuntimeException("Property owner is required in the request!");
        }
        Long ownerId = property.getOwner().getId();

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ownerId));

        property.setOwner(owner);
        owner.setRole(Role.OWNER);
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

    @Override
    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }
}
