package com.optimizely.junit5hackday.controller;

import com.optimizely.junit5hackday.entity.User;
import com.optimizely.junit5hackday.exceptions.BadRequestException;
import com.optimizely.junit5hackday.exceptions.UserExistedException;
import com.optimizely.junit5hackday.payloads.CreateUserRequest;
import com.optimizely.junit5hackday.payloads.RequestToken;
import com.optimizely.junit5hackday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.registerNewUser(request);
            return ResponseEntity.ok(user);
        } catch (BadRequestException ex) {
            return ResponseEntity.badRequest().build();
        } catch (UserExistedException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/auth/token")
    public ResponseEntity<?> requestToken(@RequestBody RequestToken requestToken) {
        
        return ResponseEntity.ok(null);
    }
}
