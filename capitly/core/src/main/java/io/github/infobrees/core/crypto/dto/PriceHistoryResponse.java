package io.github.infobrees.core.crypto.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record PriceHistoryResponse(
        @NotNull UUID coinId,
        @NotNull PriceHistoryRange range,
        @NotNull List<@NotNull PricePoint> points) {}
