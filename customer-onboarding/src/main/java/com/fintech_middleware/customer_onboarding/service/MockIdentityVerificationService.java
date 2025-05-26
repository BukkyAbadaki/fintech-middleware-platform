package com.fintech_middleware.customer_onboarding.service;

import org.springframework.stereotype.Service;

@Service
public class MockIdentityVerificationService {

    public boolean verifyBvn(String bvn) {
        // Simulate valid BVN check
        return bvn != null && bvn.matches("\\d{11}") && bvn.startsWith("22");
    }

    public boolean verifyNin(String nin) {
        // Simulate valid NIN check
        return nin != null && nin.matches("\\d{11}") && nin.startsWith("11");
    }

}
