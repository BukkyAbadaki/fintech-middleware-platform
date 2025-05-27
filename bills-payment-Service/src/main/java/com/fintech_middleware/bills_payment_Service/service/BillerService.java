package com.fintech_middleware.bills_payment_Service.service;

import com.fintech_middleware.bills_payment_Service.domain.Billers;
import com.fintech_middleware.bills_payment_Service.dto.request.BillerRequestDto;

import java.util.List;

public interface BillerService {
    Billers saveBiller(BillerRequestDto dto);
    List<Billers> getBillersByCategory(String categoryCode);
}
