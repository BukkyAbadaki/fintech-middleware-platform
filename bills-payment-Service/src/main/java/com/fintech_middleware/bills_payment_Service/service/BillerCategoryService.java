package com.fintech_middleware.bills_payment_Service.service;

import com.fintech_middleware.bills_payment_Service.domain.BillerCategory;
import com.fintech_middleware.bills_payment_Service.dto.request.BillerCategoryDto;
import com.fintech_middleware.bills_payment_Service.dto.response.BillerCategoryListResponse;

public interface BillerCategoryService {
    BillerCategory saveCategory(BillerCategoryDto dto);

    BillerCategoryListResponse getBillerCategories();
}