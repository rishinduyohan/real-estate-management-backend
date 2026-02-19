package com.pvt.ecom.model.entity;

import com.pvt.ecom.model.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "user_role")
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
