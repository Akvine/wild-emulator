package ru.akvine.wild.emulator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum SpendingBudgetType {
    CONSTANT("constant"),
    SUMMING("summing"),
    RANDOM("random");

    private final String value;

    public static SpendingBudgetType safeValueOf(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("Spending budget strategy can't be blank!");
        }

        return switch (value.toLowerCase()) {
            case "constant" -> CONSTANT;
            case "summing" -> SUMMING;
            case "random" -> RANDOM;
            default -> throw new UnsupportedOperationException("Strategy budget type = [" + value + "] is not supported!");
        };
    }
}
