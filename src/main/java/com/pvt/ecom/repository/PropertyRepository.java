package com.pvt.ecom.repository;

import com.pvt.ecom.model.PropertyStatus;
import com.pvt.ecom.model.PropertyType;
import com.pvt.ecom.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> findByOwnerId(Long ownerId);

    List<Property> findByStatus(PropertyStatus status);

    List<Property> findByType(PropertyType type);

    List<Property> getPropertyByOwnerId(Long ownerId);
}
