package com.example.commerce.user.service;

import com.example.commerce.user.dto.UserRequest;
import com.example.commerce.user.dto.UserResponse;
import com.example.commerce.user.entity.User;
import com.example.commerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public void signup(UserRequest userRequest) {
        if (userRepository.findByUserName(userRequest.getUserName())
                .isPresent()) throw new IllegalArgumentException("중복된 이름이 존재합니다");

        User user = new User(userRequest);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse login(UserRequest userRequest) {
        User user = userRepository.findByUserName(userRequest.getUserName()).orElseThrow(
                () -> new UsernameNotFoundException("유저 이름을 찾을 수 없습니다")
        );

        return UserResponse.of(user);
    }

    @Override
    public void logout() {

    }
}
