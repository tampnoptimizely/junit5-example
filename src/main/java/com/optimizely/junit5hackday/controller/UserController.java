package com.optimizely.junit5hackday.controller;

import com.optimizely.junit5hackday.entity.User;
import com.optimizely.junit5hackday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.of(Optional.ofNullable(userService.getAllUsers()));
    }
}
