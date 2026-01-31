package com.pvt.realestate.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
}
