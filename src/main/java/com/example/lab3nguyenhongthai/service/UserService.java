package com.example.lab3nguyenhongthai.service;

import com.example.lab3nguyenhongthai.entity.User;
import com.example.lab3nguyenhongthai.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
