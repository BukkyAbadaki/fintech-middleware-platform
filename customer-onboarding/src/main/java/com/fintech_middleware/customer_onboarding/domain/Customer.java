package com.fintech_middleware.customer_onboarding.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_profile")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String password;

    private String bvn;

    private String nin;

    @Column(unique = true, nullable = false)
    private String email;

    private boolean isVerified;
    private String dateOfBirth;
    private String phoneNumber;
    private String roles;





}
