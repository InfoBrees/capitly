package io.github.infobrees.core.crypto.dto;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record PricePoint(
        @NotNull Instant timestamp, @NotNull @DecimalMin("0.0") BigDecimal price) {}
