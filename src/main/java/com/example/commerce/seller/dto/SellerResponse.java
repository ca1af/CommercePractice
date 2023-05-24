package com.example.commerce.seller.dto;

import com.example.commerce.seller.entity.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerResponse {
    private Long sellerId;

    private String sellerName;

    private String sellerPassword;

    private String email;

    private String sellerNumber;

    private String sellerAddress;

    private SellerResponse(Seller seller){
        this.sellerId = seller.getId();
        this.sellerName = seller.getSellerName();
        this.sellerPassword = seller.getSellerPassword();
        this.email = seller.getEmail();
        this.sellerNumber = seller.getSellerNumber();
        this.sellerAddress = seller.getSellerAddress();
    }

    public static SellerResponse of(Seller seller){
        return new SellerResponse(seller);
    }
}
