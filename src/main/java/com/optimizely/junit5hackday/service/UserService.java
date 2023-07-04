package com.optimizely.junit5hackday.service;

import com.optimizely.junit5hackday.entity.User;
import com.optimizely.junit5hackday.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void init() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
