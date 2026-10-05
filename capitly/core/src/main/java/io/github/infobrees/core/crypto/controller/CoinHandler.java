package io.github.infobrees.core.crypto.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.infobrees.core.crypto.dto.Coin;

@RestController 
@RequestMapping("/api/v1/coins")
public class CoinHandler {
    private static final Logger logger = LoggerFactory.getLogger(CoinHandler.class);

    @GetMapping("/{coinId}")
    public ResponseEntity<Coin> getCoin(@PathVariable UUID coinId) {
        logger.info("Logging...");
        return ResponseEntity.ok(new Coin(coinId, "BTC", "Bitcoin", BigDecimal.valueOf(123141.23)));
    }
}
