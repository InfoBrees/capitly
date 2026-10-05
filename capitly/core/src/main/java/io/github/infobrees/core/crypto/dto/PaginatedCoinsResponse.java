package io.github.infobrees.core.crypto.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaginatedCoinsResponse(
        @NotNull List<@NotNull Coin> content,
        @Min(0) int page,
        @Size(min = 1, max = 100) int size,
        @Min(0) long totalElements,
        @Min(0) int totalPages,
        boolean hasNext,
        boolean hasPrevious) {}
