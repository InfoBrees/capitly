package io.github.infobrees.core.crypto.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PriceHistoryRange {
    H24("24h"),
    D7("7d"),
    M1("1m");

    private final String value;

    PriceHistoryRange(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return value;
    }

    @JsonCreator
    public static PriceHistoryRange fromValue(String value) {
        for (PriceHistoryRange range : values()) {
            if (range.value.equals(value)) {
                return range;
            }
        }
        throw new IllegalArgumentException("Unknown price history range: " + value);
    }
}
