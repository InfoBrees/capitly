package io.github.infobrees.core.crypto.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record LiveCoin(
        @NotNull UUID coinId,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull Instant timestamp) {}
