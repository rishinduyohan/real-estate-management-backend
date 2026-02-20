package com.pvt.ecom.controller;

import com.pvt.ecom.model.entity.Property;
import com.pvt.ecom.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Property>> getPropertiesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(propertyService.getPropertiesByOwner(ownerId));
    }

    @PostMapping("/add")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property savedProperty = propertyService.addProperty(property);
        return ResponseEntity.ok(savedProperty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        if (propertyService.deleteProperty(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}