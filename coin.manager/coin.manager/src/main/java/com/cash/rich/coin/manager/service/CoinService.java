package com.cash.rich.coin.manager.service;

import com.cash.rich.coin.manager.entity.Coin;
import com.cash.rich.coin.manager.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CoinService {
    @Autowired
    private CoinRepository coinRepository;

    public Optional<Coin> fetchAndStoreCoinData(Long userId) {
        String apiUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETHLTC";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", "27ab17d1-215f-49e5-9ca4-afd48810c149");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Coin coin = new Coin();
            coin.setId(userId);
            coin.setResponse(response.getBody());
            coin.setTimestamp(LocalDateTime.now());
            coinRepository.save(coin);
            return Optional.of(coin);
        } else {
            return Optional.empty();
        }
    }
}
