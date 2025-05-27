package com.fintech_middleware.bills_payment_Service.controller;

import com.fintech_middleware.bills_payment_Service.config.OnboardingClient;
import com.fintech_middleware.bills_payment_Service.domain.Billers;
import com.fintech_middleware.bills_payment_Service.dto.request.BillerRequestDto;
import com.fintech_middleware.bills_payment_Service.dto.request.UserDto;
import com.fintech_middleware.bills_payment_Service.service.BillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billers")
public class BillerController {
    @Autowired
    private  BillerService billerService;
    @Autowired
    private OnboardingClient onboardingClient;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // restrict access to ADMIN role
    public ResponseEntity<Billers> createBiller(
            @RequestBody BillerRequestDto dto,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extract token from Authorization header (e.g., "Bearer eyJ...")
        String token = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        } else {
            throw new AccessDeniedException("Missing or invalid Authorization header");
        }

        // Validate token via onboarding service
        if (!onboardingClient.validateToken(token)) {
            throw new AccessDeniedException("Invalid token");
        }

        // Optionally get user info if you want to use user data for logic
        UserDto user = onboardingClient.getUserInfo(token);

        // Proceed with saving biller
        Billers saved = billerService.saveBiller(dto);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/by-category/{categoryCode}")
    public ResponseEntity<List<Billers>> getBillersByCategory(@PathVariable String categoryCode) {
        return ResponseEntity.ok(billerService.getBillersByCategory(categoryCode));
    }

}
