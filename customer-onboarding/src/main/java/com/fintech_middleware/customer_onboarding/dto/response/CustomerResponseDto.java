package com.fintech_middleware.customer_onboarding.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {

    private String responseMessage;
    private String responseCode;
    private Long id;
    private String fullName;
    private String bvn;
    private String nin;
    private String email;

}
