package com.fintech_middleware.bills_payment_Service.dto.response;

import com.fintech_middleware.bills_payment_Service.dto.request.BillerCategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class BillerCategoryListResponse {
    private List<BillerCategoryDto> category;

}
