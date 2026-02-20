package com.pvt.ecom.model.dto;

import com.pvt.ecom.model.Role;
import lombok.*;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String imageUrl;
    private Role role;
}
