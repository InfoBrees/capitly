package io.github.infobrees.core.crypto.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.infobrees.core.crypto.dto.AddCoinToWatchlistRequest;
import io.github.infobrees.core.crypto.dto.Coin;
import io.github.infobrees.core.crypto.dto.Watchlist;

@RestController 
@RequestMapping("/api/v1/watchlists")
public class WatchlistHandler {
    private static final Logger logger = LoggerFactory.getLogger(WatchlistHandler.class);

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlist(@PathVariable UUID watchlistId) {
        logger.info("Logging...");
        return ResponseEntity.ok(new Watchlist(watchlistId, UUID.randomUUID(), List.of()));
    }

    @GetMapping("/{watchlistId}/coins")
    public ResponseEntity<List<Coin>> getWatchlistCoins(@PathVariable UUID watchlistId) {
        logger.info("Logging...");
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/{watchlistId}/coins")
    public ResponseEntity<Void> addCoinToWatchlist(
            @PathVariable UUID watchlistId, @RequestBody AddCoinToWatchlistRequest request) {
        logger.info("Logging...");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<Watchlist> createWatchlist() {
        logger.info("Logging...");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Watchlist(UUID.randomUUID(), UUID.randomUUID(), List.of()));
    }

    @DeleteMapping("/{watchlistId}/coins/{coinId}")
    public ResponseEntity<Void> removeCoinFromWatchlist(
            @PathVariable UUID watchlistId, @PathVariable UUID coinId) {
        logger.info("Logging...");
        return ResponseEntity.noContent().build();
    }
}