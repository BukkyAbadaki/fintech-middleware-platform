package com.fintech_middleware.bills_payment_Service.dto.request;

import lombok.Data;

@Data
public class BillerCategoryDto {
    private Long id;
    private String billerCategoryCode;
    private String billerCategoryName;
    private String billerCategoryDescription;

    private String logoURL;
    private String status;

}
