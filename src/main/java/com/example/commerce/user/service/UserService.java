package com.example.commerce.user.service;

import com.example.commerce.user.dto.UserRequest;
import com.example.commerce.user.dto.UserResponse;

public interface UserService {
    void signup(UserRequest userRequest);

    UserResponse login(UserRequest userRequest);

    void logout();
}
