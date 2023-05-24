package com.example.commerce.seller.entity;

import com.example.commerce.seller.dto.SellerRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 이름, 사업자번호, 사업자 주소, 전화번호, 이멩리 비밀번호

    private String sellerName;

    private String sellerPassword;

    private String email;

    private String sellerNumber;

    private String sellerAddress;
    @Builder
    public Seller(String sellerName, String sellerPassword, String email, String sellerNumber, String sellerAddress) {
        this.sellerName = sellerName;
        this.sellerPassword = sellerPassword;
        this.email = email;
        this.sellerNumber = sellerNumber;
        this.sellerAddress = sellerAddress;
    }

    public Seller(SellerRequest sellerRequest){
        this.sellerName = sellerRequest.getSellerName();
        this.sellerPassword = sellerRequest.getSellerPassword();
        this.email = sellerRequest.getEmail();
        this.sellerNumber = sellerRequest.getSellerNumber();
        this.sellerAddress = sellerRequest.getSellerAddress();
    }
}
