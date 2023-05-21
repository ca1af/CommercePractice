package com.example.commerce.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;

    // 회원가입 시 사용
    public UserRequest(String userName, String email, String password, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    // 로그인 시 사용
    public UserRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
