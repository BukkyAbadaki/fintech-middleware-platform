package com.fintech_middleware.bills_payment_Service.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userId;           // Unique identifier for the user
    private String username;         // Username or email
    private String email;            // User email
    private String roles;      // List of roles or authorities (e.g., ROLE_USER, ROLE_ADMIN)
    private String fullName;
}
