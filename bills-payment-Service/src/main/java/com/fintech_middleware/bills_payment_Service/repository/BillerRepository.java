package com.fintech_middleware.bills_payment_Service.repository;

import com.fintech_middleware.bills_payment_Service.domain.Billers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillerRepository extends JpaRepository<Billers, Long> {
    Optional<Billers> findByBillerCode(String billerCode);
    List<Billers> findByCategoryCode(String categoryCode);

}
