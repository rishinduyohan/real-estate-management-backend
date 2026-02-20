package com.pvt.ecom.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pvt.ecom.model.PropertyStatus;
import com.pvt.ecom.model.PropertyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ElementCollection
    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "image_url")
    private List<String> images;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "property_type")
    private PropertyType type;

    private String location;
    private Double price;
    private String size;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "property_status")
    private PropertyStatus status;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnoreProperties("properties")
    private User owner;

    @Embedded
    private PropertyDetails details;

    @CreationTimestamp
    private LocalDateTime createdAt;
}