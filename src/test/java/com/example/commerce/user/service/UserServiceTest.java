package com.example.commerce.user.service;

import com.example.commerce.user.dto.UserRequest;
import com.example.commerce.user.entity.User;
import com.example.commerce.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    static UserRequest request = UserRequest.
            builder()
            .email("email")
            .password("password")
            .phoneNumber("phoneNumber")
            .build();

    @Test
    void save(){
        User user = new User(request);
        userRepository.save(user);
    }


}