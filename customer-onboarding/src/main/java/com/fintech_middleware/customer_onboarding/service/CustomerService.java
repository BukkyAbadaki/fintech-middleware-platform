package com.fintech_middleware.customer_onboarding.service;

import com.fintech_middleware.customer_onboarding.dto.request.CustomerRequestDto;
import com.fintech_middleware.customer_onboarding.dto.request.LoginRequest;
import com.fintech_middleware.customer_onboarding.dto.response.AuthResponse;
import com.fintech_middleware.customer_onboarding.dto.response.CustomerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerResponseDto onboardCustomer(CustomerRequestDto requestDto);
    CustomerResponseDto getCustomerById(Long id);
    List<CustomerResponseDto> getAllCustomers();
    AuthResponse login(LoginRequest request);

}
