package io.github.infobrees.core.crypto.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Coin(
        @NotNull UUID coinId,
        @NotBlank String symbol,
        @NotBlank String name,
        @NotNull @DecimalMin("0.0") BigDecimal price) {}
