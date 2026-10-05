package io.github.infobrees.core.crypto.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WatchlistCoin(@NotNull UUID coinId, @NotBlank String symbol, @NotBlank String name) {}
