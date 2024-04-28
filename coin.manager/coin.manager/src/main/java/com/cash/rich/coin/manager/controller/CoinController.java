package com.cash.rich.coin.manager.controller;

import com.cash.rich.coin.manager.entity.Coin;
import com.cash.rich.coin.manager.service.CoinService;
import com.cash.rich.coin.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/coin")
public class CoinController {
    @Autowired
    private CoinService coinService;

    @Autowired
    private UserService userService;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchCoinData(@RequestParam String username) {
        Long userId = userService.getIdByUserName(username);

        Optional<Coin> coinData = coinService.fetchAndStoreCoinData(userId);
        if(coinData.isPresent()){
            return ResponseEntity.ok(coinData.get());
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Couldn't fetch Coin data");
        }
    }
}
