package com.fintech_middleware.bills_payment_Service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "biller-category")
public class BillerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String billerCategoryCode;
    private String billerCategoryDescription;


    private String billerCategoryName;

    private String logoURL;
    private String status;

    public BillerCategory(String billerCategoryCode, String billerCategoryName, String billerCategoryDescription, String logoURL, String status) {


    }
}
