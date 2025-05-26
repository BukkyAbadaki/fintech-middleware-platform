package com.fintech_middleware.customer_onboarding.service;

import com.fintech_middleware.customer_onboarding.config.JwtUtil;
import com.fintech_middleware.customer_onboarding.domain.Customer;
import com.fintech_middleware.customer_onboarding.dto.request.CustomerRequestDto;
import com.fintech_middleware.customer_onboarding.dto.request.LoginRequest;
import com.fintech_middleware.customer_onboarding.dto.response.AuthResponse;
import com.fintech_middleware.customer_onboarding.dto.response.CustomerResponseDto;
import com.fintech_middleware.customer_onboarding.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final MockIdentityVerificationService identityVerificationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;




    @Override
    public CustomerResponseDto onboardCustomer(CustomerRequestDto requestDto) {
        try {
            // Check if email already exists
            Optional<Customer> existingCustomer = customerRepository.findByEmail(requestDto.getEmail());
            if (existingCustomer.isPresent()) {
                return CustomerResponseDto.builder()
                        .responseCode("001")
                        .responseMessage("Email already exists")
                        .build();
            }
            // 👉 Verify BVN and NIN
            boolean isBvnValid = identityVerificationService.verifyBvn(requestDto.getBvn());
            boolean isNinValid = identityVerificationService.verifyNin(requestDto.getNin());

            if (!isBvnValid) {
                return CustomerResponseDto.builder()
                        .responseCode("002")
                        .responseMessage("Invalid BVN")
                        .build();
            }

            if (!isNinValid) {
                return CustomerResponseDto.builder()
                        .responseCode("003")
                        .responseMessage("Invalid NIN")
                        .build();
            }


            // Save new customer
            Customer customer = Customer.builder()
                    .fullName(requestDto.getFullName())
                    .bvn(requestDto.getBvn())
                    .nin(requestDto.getNin())
                    .dateOfBirth(requestDto.getDateOfBirth())
                    .phoneNumber(requestDto.getPhoneNumber())
                    .email(requestDto.getEmail())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .isVerified(true)
                    .build();

            Customer saved = customerRepository.save(customer);

            // Return successful response
            return CustomerResponseDto.builder()
                    .id(saved.getId())
                    .fullName(saved.getFullName())
                    .bvn(saved.getBvn())
                    .nin(saved.getNin())
                    .email(saved.getEmail())
                    .responseCode("000")
                    .responseMessage("Success")
                    .build();

        } catch (Exception ex) {
            ex.printStackTrace(); // Or log it
            return CustomerResponseDto.builder()
                    .responseCode("999")
                    .responseMessage("An unexpected error occurred")
                    .build();
        }
    }


    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + id));

            return CustomerResponseDto.builder()
                    .id(customer.getId())
                    .fullName(customer.getFullName())
                    .bvn(customer.getBvn())
                    .nin(customer.getNin())
                    .email(customer.getEmail())
                    .responseCode("000")
                    .responseMessage("Success")
                    .build();

        } catch (NoSuchElementException ex) {
            return CustomerResponseDto.builder()
                    .responseCode("404")
                    .responseMessage(ex.getMessage())
                    .build();

        } catch (Exception ex) {
            ex.printStackTrace(); // or use logger
            return CustomerResponseDto.builder()
                    .responseCode("002")
                    .responseMessage("An unexpected error occurred")
                    .build();
        }
    }
    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        try {
            List<Customer> customers = customerRepository.findAll();

            if (customers.isEmpty()) {
                return Collections.singletonList(
                        CustomerResponseDto.builder()
                                .responseCode("404")
                                .responseMessage("No customers found")
                                .build()
                );
            }

            return customers.stream()
                    .map(customer -> CustomerResponseDto.builder()
                            .id(customer.getId())
                            .fullName(customer.getFullName())
                            .bvn(customer.getBvn())
                            .nin(customer.getNin())
                            .email(customer.getEmail())
                            .responseCode("000")
                            .responseMessage("Success")
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace(); // or use a logger
            return Collections.singletonList(
                    CustomerResponseDto.builder()
                            .responseCode("999")
                            .responseMessage("An unexpected error occurred")
                            .build()
            );
        }
    }
    @Override
    public AuthResponse login(LoginRequest request) {
        Customer user = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token,"000","login successful" );
    }



}
