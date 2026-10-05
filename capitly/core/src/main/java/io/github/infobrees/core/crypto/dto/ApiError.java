package io.github.infobrees.core.crypto.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record ApiError(
        @NotBlank String code, @NotBlank String message, Instant timestamp, String path) {}
