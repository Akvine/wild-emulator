package ru.akvine.wild.emulator.core.strategy.budget;

import org.springframework.stereotype.Component;

@Component
public class SpendingBudgetValidator {
    public void validate(int value, long seconds) {
        if (value < 0) {
            String errorMessage = String.format(
                    "Spending budget value can't be less than 0. Value = [%s]",
                    value);
            throw new IllegalArgumentException(errorMessage);
        }
        if (seconds < 0) {
            String errorMessage = String.format(
                    "Spending delay seconds can't be less than 0. Value = [%s]",
                    seconds);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
