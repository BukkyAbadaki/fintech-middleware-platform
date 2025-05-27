package com.fintech_middleware.bills_payment_Service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Billers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billerCode;
    private String billerName;
    private String categoryCode;
    private String logoURL;
    private String status;

    public Billers(String billerCode, String billerName, String categoryCode, String logoURL, String status) {

    }
}
