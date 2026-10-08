package io.github.infobrees.core.crypto.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AddCoinToWatchlistRequest(@NotNull UUID coinId) {}
