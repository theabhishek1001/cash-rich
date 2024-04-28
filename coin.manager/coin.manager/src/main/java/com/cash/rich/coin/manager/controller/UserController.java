package com.cash.rich.coin.manager.controller;

import com.cash.rich.coin.manager.entity.LoginCredentials;
import com.cash.rich.coin.manager.entity.AppUser;
import com.cash.rich.coin.manager.entity.UserDto;
import com.cash.rich.coin.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AppUser appUser) {
        boolean signedUp = userService.signUp(appUser);
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

    @PutMapping("update/{username}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody UserDto user) {
        boolean updated = userService.updateUser(username, user);
        if(updated) {
            return ResponseEntity.ok("User details updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Home page");
    }

}
