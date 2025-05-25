package com.fintech_middleware.customer_onboarding.service;

import com.fintech_middleware.customer_onboarding.domain.Customer;
import com.fintech_middleware.customer_onboarding.dto.request.CustomerRequestDto;
import com.fintech_middleware.customer_onboarding.dto.response.CustomerResponseDto;
import com.fintech_middleware.customer_onboarding.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public CustomerResponseDto onboardCustomer(CustomerRequestDto requestDto) {

        try {
            // Save new customer
            Customer customer = Customer.builder()
                    .fullName(requestDto.getFullName())
                    .bvn(requestDto.getBvn())
                    .nin(requestDto.getNin())
                    .email(requestDto.getEmail())
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

        } catch (DataIntegrityViolationException ex) {
            // Likely due to unique constraint violations
            return CustomerResponseDto.builder()
                    .responseCode("002")
                    .responseMessage("Duplicate BVN or NIN")
                    .build();

        } catch (Exception ex) {
            // Log the exception if needed
            ex.printStackTrace(); // Or use a logger

            return CustomerResponseDto.builder()
                    .responseCode("999")
                    .responseMessage("An unexpected error occurred")
                    .build();
        }
}


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


}
