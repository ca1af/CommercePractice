package com.example.commerce.user.controller;

import com.example.commerce.auth.dto.TokenResponse;
import com.example.commerce.auth.jwt.JwtProvider;
import com.example.commerce.user.dto.UserRequest;
import com.example.commerce.user.dto.UserResponse;
import com.example.commerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    @PostMapping("api/users/signup")
    public void signUp(@RequestBody UserRequest userRequest){
        userService.signup(userRequest);
    }
    @PostMapping("api/users/login")
    public TokenResponse login(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.login(userRequest);
        return jwtProvider.createTokensByLogin(userResponse);
    }
}
