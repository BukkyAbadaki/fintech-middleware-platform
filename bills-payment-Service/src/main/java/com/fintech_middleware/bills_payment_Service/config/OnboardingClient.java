package com.fintech_middleware.bills_payment_Service.config;

import com.fintech_middleware.bills_payment_Service.dto.request.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OnboardingClient {

    @Autowired
    private  RestTemplate restTemplate;
    private final String onboardingBaseUrl = "http://localhost:8085/api/v1";

//    public OnboardingClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }


    public boolean validateToken(String token) {
        String url = onboardingBaseUrl + "/validateToken?token=" + token;
        Boolean isValid = restTemplate.getForObject(url, Boolean.class);
        return isValid != null && isValid;
    }

    public UserDto getUserInfo(String token) {
        String url = onboardingBaseUrl + "/userInfo?token=" + token;
        return restTemplate.getForObject(url, UserDto.class);
    }

}
