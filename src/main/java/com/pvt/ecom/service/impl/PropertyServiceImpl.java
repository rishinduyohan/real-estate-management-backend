package com.pvt.ecom.service.impl;

import com.pvt.ecom.model.Role;
import com.pvt.ecom.model.dto.PropertyDTO;
import com.pvt.ecom.model.entity.Property;
import com.pvt.ecom.model.entity.User;
import com.pvt.ecom.model.mapper.PropertyMapper;
import com.pvt.ecom.repository.PropertyRepository;
import com.pvt.ecom.repository.UserRepository;
import com.pvt.ecom.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();

        return propertyMapper.toDTOList(properties);
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return propertyMapper.toDTO(property);
    }

    @Override
    public PropertyDTO addProperty(PropertyDTO property) {
        if (property.getOwnerId() == null) {
            log.info("Property owner is required in the request!");
        }
        Long ownerId = property.getOwnerId();

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + ownerId));

        Property propertyEntity = propertyMapper.toEntity(property);

        propertyEntity.setOwner(owner);
        owner.setRole(Role.OWNER);
        return propertyMapper.toDTO(propertyRepository.save(propertyEntity));
    }

    @Override
    public boolean deleteProperty(Long id) {
        propertyRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PropertyDTO> getPropertiesByOwner(Long ownerId) {
        List<Property> properties = propertyRepository.getPropertyByOwnerId(ownerId);

        return propertyMapper.toDTOList(properties);
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO property) {
        if (property.getId() != null){
            Property exist =  propertyMapper.toEntity(property);
            return propertyMapper.toDTO(propertyRepository.save(exist));
        }else{
            throw new RuntimeException("Property id is required");
        }
    }
}
