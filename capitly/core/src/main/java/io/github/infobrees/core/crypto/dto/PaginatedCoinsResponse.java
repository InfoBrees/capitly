package io.github.infobrees.core.crypto.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PaginatedCoinsResponse(
        @NotNull List<@NotNull Coin> content,
        @Min(0) int page,
        @Min(1) int size,
        @Min(0) long totalElements,
        @Min(0) int totalPages,
        boolean hasNext,
        boolean hasPrevious) {}
