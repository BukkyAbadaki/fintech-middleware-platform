package com.fintech_middleware.bills_payment_Service.dto.request;

import lombok.Data;

@Data
public class BillerRequestDto {
    private String billerCode;
    private String billerName;
    private String categoryCode;
    private String logoURL;
    private String status;

}
