package com.cash.rich.coin.manager.controller;

import com.cash.rich.coin.manager.entity.LoginCredentials;
import com.cash.rich.coin.manager.entity.User;
import com.cash.rich.coin.manager.entity.UserDto;
import com.cash.rich.coin.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        boolean signedUp = userService.signUp(user);
        if(signedUp){
            return ResponseEntity.status(HttpStatus.CREATED).body("User signed up successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials loginCredentials) {
        Optional<UserDto> loggedInUser = userService.login(loginCredentials.getUsername(), loginCredentials.getPassword());

        if(loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

}
