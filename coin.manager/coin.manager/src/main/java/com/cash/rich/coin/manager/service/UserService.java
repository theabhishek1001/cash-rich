package com.cash.rich.coin.manager.service;

import com.cash.rich.coin.manager.entity.AppUser;
import com.cash.rich.coin.manager.entity.UserDto;
import com.cash.rich.coin.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signUp(AppUser appUser) {
        //Checking if the username already exists
        if(userRepository.findByUsername(appUser.getUsername()) != null) {
            return false;
        }

        userRepository.save(appUser);
        return true;
    }

    public Optional<UserDto> login(String username, String password) {
        AppUser appUser = userRepository.findByUsername(username);
        if(appUser != null && password.equals(appUser.getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setFirstName(appUser.getFirstName());
            userDto.setLastName(appUser.getLastName());
            userDto.setEmail(appUser.getEmail());
            userDto.setMobile(appUser.getMobile());
            return Optional.of(userDto);
        } else {
            return Optional.empty();
        }
    }

    public boolean updateUser(String username, UserDto updateRequest) {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser != null) {
            if (updateRequest.getFirstName() != null) {
                appUser.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null) {
                appUser.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getMobile() != null) {
                appUser.setMobile(updateRequest.getMobile());
            }
            if (updateRequest.getPassword() != null) {
                appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            }
            userRepository.save(appUser);
            return true;
        } else {
            return false;
        }
    }

    public Long getIdByUserName(String username) {
        return userRepository.findByUsername(username).getId();
    }


}
