package com.example.commerce.user.service;

import com.example.commerce.user.entity.User;
import com.example.commerce.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void save(){
//        userRepository.save();
    }
}