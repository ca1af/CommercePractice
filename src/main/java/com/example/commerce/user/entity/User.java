package com.example.commerce.user.entity;

import com.example.commerce.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    //이메일, 전번, 비번
    private String email;
    private String password;
    private String phoneNumber;

    @Builder
    public User(String userName, String email, String password, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    // Request 를 이용한 signUp 생성자
    public User(UserRequest userRequest) {
        this.userName = userRequest.getUserName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.phoneNumber = userRequest.getPhoneNumber();
    }
}
