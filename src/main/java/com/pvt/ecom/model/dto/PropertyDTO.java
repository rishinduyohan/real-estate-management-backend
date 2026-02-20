package com.pvt.ecom.model.dto;

import com.pvt.ecom.model.entity.PropertyDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropertyDTO {
    private Long id;
    private String title;
    private String type;
    private String location;
    private Double price;
    private String size;
    private String status;
    private String imageUrl;
    private Long ownerId;
    private String ownerName;
    private PropertyDetails details;
}
