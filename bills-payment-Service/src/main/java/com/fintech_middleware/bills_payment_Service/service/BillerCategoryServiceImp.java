package com.fintech_middleware.bills_payment_Service.service;

import com.fintech_middleware.bills_payment_Service.domain.BillerCategory;
import com.fintech_middleware.bills_payment_Service.dto.request.BillerCategoryDto;
import com.fintech_middleware.bills_payment_Service.dto.response.BillerCategoryListResponse;
import com.fintech_middleware.bills_payment_Service.repository.BillerCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillerCategoryServiceImp implements BillerCategoryService {

@Autowired
    private  BillerCategoryRepository billerCategoryRepository;
    private final Logger logger = LoggerFactory.getLogger(BillerCategoryServiceImp.class);


    @Override
    public BillerCategory saveCategory(BillerCategoryDto dto) {

            BillerCategory category = new BillerCategory(
                    dto.getBillerCategoryCode(),
                    dto.getBillerCategoryName(),
                    dto.getBillerCategoryDescription(),
                    dto.getLogoURL(),
                    dto.getStatus()
            );
            return billerCategoryRepository.save(category);
        }


    public BillerCategoryListResponse getBillerCategories() {
        BillerCategoryListResponse response = new BillerCategoryListResponse();
        try {
            List<BillerCategory> listC = billerCategoryRepository.findAll();
            List<BillerCategoryDto> categoryList = listC.stream().map(cat -> {
                BillerCategoryDto category = new BillerCategoryDto();
                category.setId(cat.getId());
                category.setBillerCategoryCode(cat.getBillerCategoryCode());
                category.setBillerCategoryName(cat.getBillerCategoryName());
                category.setLogoURL(cat.getLogoURL());
                category.setStatus(cat.getStatus());
                return category;
            }).collect(Collectors.toList());

            response.setCategory(categoryList);
        } catch (Exception e) {
            logger.error("Error retrieving biller categories", e);
        }
        return response;
    }
}
