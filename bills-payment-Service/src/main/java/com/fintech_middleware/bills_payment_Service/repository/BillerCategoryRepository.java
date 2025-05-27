package com.fintech_middleware.bills_payment_Service.repository;

import com.fintech_middleware.bills_payment_Service.domain.BillerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillerCategoryRepository extends JpaRepository<BillerCategory, Long> {
    Optional<BillerCategory> findByBillerCategoryCode(String billerCategoryCode);

}
