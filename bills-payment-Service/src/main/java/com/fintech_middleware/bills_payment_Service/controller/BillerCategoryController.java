package com.fintech_middleware.bills_payment_Service.controller;

import com.fintech_middleware.bills_payment_Service.dto.response.BillerCategoryListResponse;
import com.fintech_middleware.bills_payment_Service.service.BillerCategoryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/billers")
@RequiredArgsConstructor
public class BillerCategoryController {

    @Autowired
    private  BillerCategoryServiceImp billerService;


    @GetMapping("/categories")
    public ResponseEntity<BillerCategoryListResponse> getCategories() {
        BillerCategoryListResponse response = billerService.getBillerCategories();
        return ResponseEntity.ok(response);
    }


}
