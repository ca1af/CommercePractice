package com.example.commerce.user.dto;

import com.example.commerce.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;

    private UserResponse(User user) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
    }

    public static UserResponse of(User user){
        return new UserResponse(user);
    }
}
