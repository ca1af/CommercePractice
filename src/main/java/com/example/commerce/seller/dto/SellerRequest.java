package com.example.commerce.seller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SellerRequest {

    private String sellerName;

    private String sellerPassword;

    private String email;

    private String sellerNumber;

    private String sellerAddress;

    // signup, Test 시 활용
    @Builder
    public SellerRequest(String sellerName, String sellerPassword, String email, String sellerNumber, String sellerAddress) {
        this.sellerName = sellerName;
        this.sellerPassword = sellerPassword;
        this.email = email;
        this.sellerNumber = sellerNumber;
        this.sellerAddress = sellerAddress;
    }
    // login 시 사용
    public SellerRequest(String sellerName, String sellerPassword) {
        this.sellerName = sellerName;
        this.sellerPassword = sellerPassword;
    }
}
