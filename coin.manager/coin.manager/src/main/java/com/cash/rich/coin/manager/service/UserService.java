package com.cash.rich.coin.manager.service;

import com.cash.rich.coin.manager.entity.User;
import com.cash.rich.coin.manager.entity.UserDto;
import com.cash.rich.coin.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean signUp(User user) {
        //Checking if the username already exists
        if(userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

    public Optional<UserDto> login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && password.equals(user.getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setMobile(user.getMobile());
            return Optional.of(userDto);
        } else {
            return Optional.empty();
        }
    }

}
