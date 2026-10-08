package io.github.infobrees.core.crypto.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record Watchlist(
        @NotNull UUID watchlistId, @NotNull UUID userId, @NotNull List<@NotNull UUID> coins) {}
