package io.github.infobrees.core.crypto.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PriceHistoryResponse(
        @NotNull UUID coinId,
        @NotBlank @Pattern(regexp = "24h|7d|1m") String range,
        @NotNull List<@NotNull PricePoint> points) {}
