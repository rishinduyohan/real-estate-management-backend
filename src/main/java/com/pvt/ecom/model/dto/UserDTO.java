package com.pvt.ecom.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String fullName;
    private String email;
    private String phone;
}
