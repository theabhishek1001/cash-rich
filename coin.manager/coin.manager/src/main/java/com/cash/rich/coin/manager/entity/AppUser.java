package com.cash.rich.coin.manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 4, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$", message = "Username must be alphanumeric and between 4 to 15 characters")
    private String username;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must be 8 to 15 characters long with at least one uppercase letter, one lowercase letter, one digit, and one special character")
    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private Long mobile;

}
