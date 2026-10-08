package io.github.infobrees.core.crypto.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.infobrees.core.crypto.dto.Coin;
import io.github.infobrees.core.crypto.dto.LiveCoin;
import io.github.infobrees.core.crypto.dto.PaginatedCoinsResponse;
import io.github.infobrees.core.crypto.dto.PriceHistoryRange;
import io.github.infobrees.core.crypto.dto.PriceHistoryResponse;

@RestController
@RequestMapping("/api/v1/coins")
public class CoinHandler {
    private static final Logger logger = LoggerFactory.getLogger(CoinHandler.class);

    @GetMapping("/{coinId}")
    public ResponseEntity<Coin> getCoin(@PathVariable UUID coinId) {
        logger.info("Logging...");
        return ResponseEntity.ok(new Coin(coinId, "BTC", "Bitcoin", BigDecimal.valueOf(123141.23)));
    }

    @GetMapping("/live/{coinId}")
    public ResponseEntity<LiveCoin> getLiveCoin(@PathVariable UUID coinId) {
        logger.info("Logging...");
        return ResponseEntity.ok(
                new LiveCoin(coinId, BigDecimal.valueOf(123141.23), Instant.now()));
    }

    @GetMapping("/history/{coinId}")
    public ResponseEntity<PriceHistoryResponse> getPriceHistory(
            @PathVariable UUID coinId,
            @RequestParam Optional<String> range,
            @RequestParam Optional<String> from,
            @RequestParam Optional<String> to) {
        logger.info("Logging...");
            return ResponseEntity.ok(new PriceHistoryResponse(
                    coinId, range.map(PriceHistoryRange::fromValue).orElse(PriceHistoryRange.H24), List.of()));
    }

    @GetMapping
    public ResponseEntity<PaginatedCoinsResponse> getPaginatedCoins(
            @RequestParam Integer page, @RequestParam Integer size) {
        logger.info("Logging...");
        return ResponseEntity.ok(
                new PaginatedCoinsResponse(List.of(), page, size, 1231244, 213, true, true));
    }
}
