package com.fintech_middleware.customer_onboarding.dto.request;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "BVN is required")
    @Size(min = 11, max = 11, message = "BVN must be 11 digits")
    private String bvn;

    @NotBlank(message = "NIN is required")
    @Size(min = 11, max = 11, message = "NIN must be 11 digits")
    private String nin;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;


}
