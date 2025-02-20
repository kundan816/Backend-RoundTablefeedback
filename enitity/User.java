package com.assignm4.RTFeedbackkkkk.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role; // ADMIN, EMPLOYEE

    @Enumerated(EnumType.STRING)
    private BandLevel bandLevel; // B6, B7, B8
}