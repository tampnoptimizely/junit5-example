package com.optimizely.junit5hackday.service;

import com.google.common.hash.Hashing;
import com.optimizely.junit5hackday.entity.User;
import com.optimizely.junit5hackday.exceptions.BadRequestException;
import com.optimizely.junit5hackday.exceptions.UserExistedException;
import com.optimizely.junit5hackday.payloads.CreateUserRequest;
import com.optimizely.junit5hackday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static String encodePassword(String pwd) {
        return Hashing.sha256().hashString(pwd, StandardCharsets.UTF_8).toString();
    }

    public User registerNewUser(CreateUserRequest request) {
        // Bad request case
        if (Objects.isNull(request)
                || !StringUtils.hasText(request.getUsername())
                || !StringUtils.hasText(request.getPassword())) {
            throw new BadRequestException();
        }
        String username = Optional.of(request.getUsername()).orElse("");
        String role = Optional.ofNullable(request.getRole()).orElse("user");

        // Email is already exists!
        Optional<User> optionalUser = userRepository.findByUsername(username.trim());
        if (optionalUser.isPresent()) {
            throw new UserExistedException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodePassword(request.getPassword()));
        user.setRole(role);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
