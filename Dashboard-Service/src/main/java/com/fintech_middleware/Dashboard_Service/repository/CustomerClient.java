package com.fintech_middleware.Dashboard_Service.repository;

import com.fintech_middleware.Dashboard_Service.dto.request.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8085/api/v1/customers")
public interface CustomerClient {
    @GetMapping("/api/customers/{id}")
    CustomerDto getCustomerById(@PathVariable long id);

}
