package com.fintech_middleware.bills_payment_Service.service;

import com.fintech_middleware.bills_payment_Service.domain.Billers;
import com.fintech_middleware.bills_payment_Service.dto.request.BillerRequestDto;
import com.fintech_middleware.bills_payment_Service.repository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillerServiceImpl implements BillerService{
    @Autowired
    private BillerRepository billerRepository;

    @Override
    public Billers saveBiller(BillerRequestDto dto) {
        Billers biller = new Billers (
                dto.getBillerCode(),
                dto.getBillerName(),
                dto.getCategoryCode(),
                dto.getLogoURL(),
                dto.getStatus()
        );
        return billerRepository.save(biller);
    }

    @Override
    public List<Billers> getBillersByCategory(String categoryCode) {
        return billerRepository.findByCategoryCode(categoryCode);
    }

}
