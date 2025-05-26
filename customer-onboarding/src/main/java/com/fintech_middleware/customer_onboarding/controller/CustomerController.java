package com.fintech_middleware.customer_onboarding.controller;

import com.fintech_middleware.customer_onboarding.dto.request.CustomerRequestDto;
import com.fintech_middleware.customer_onboarding.dto.request.LoginRequest;
import com.fintech_middleware.customer_onboarding.dto.response.AuthResponse;
import com.fintech_middleware.customer_onboarding.dto.response.CustomerResponseDto;
import com.fintech_middleware.customer_onboarding.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/onboard")
    public ResponseEntity<CustomerResponseDto> onboardCustomer(@Valid  @RequestBody CustomerRequestDto request) {
        return ResponseEntity.ok(customerService.onboardCustomer(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<CustomerResponseDto> customers = customerService.getAllCustomers();

        // Check if the only item is an error (404 or 999)
        if (customers.size() == 1 && !"000".equals(customers.get(0).getResponseCode())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customers);
        }

        return ResponseEntity.ok(customers);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }



}
