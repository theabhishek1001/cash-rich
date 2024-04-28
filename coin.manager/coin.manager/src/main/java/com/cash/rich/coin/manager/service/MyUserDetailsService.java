package com.cash.rich.coin.manager.service;

import com.cash.rich.coin.manager.entity.AppUser;
import com.cash.rich.coin.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser != null) {
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
