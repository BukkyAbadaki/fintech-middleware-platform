package com.fintech_middleware.customer_onboarding.repository;

import com.fintech_middleware.customer_onboarding.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByBvn(String bvn);
    Optional<Customer> findByNin(String nin);
    Optional<Customer> findByEmail(String email);


}
