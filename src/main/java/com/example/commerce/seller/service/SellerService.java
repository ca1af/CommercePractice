package com.example.commerce.seller.service;

import com.example.commerce.seller.dto.SellerRequest;
import com.example.commerce.seller.dto.SellerResponse;

public interface SellerService {
    void signUp(SellerRequest sellerRequest);

    SellerResponse login(SellerRequest sellerRequest);

    void logout();
}
