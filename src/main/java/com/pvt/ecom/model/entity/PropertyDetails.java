package com.pvt.ecom.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetails {
    private Integer bedrooms;
    private Integer bathrooms;
    @Column(columnDefinition = "TEXT")
    private String description;
}
