package ru.akvine.wild.emulator.core.strategy.budget;

import java.time.LocalDateTime;

public abstract class CommonSpendingBudgetStrategy implements SpendingBudgetStrategy {
    protected final int value;
    protected final long delaySeconds;
    protected LocalDateTime nextStart = LocalDateTime.now();

    protected CommonSpendingBudgetStrategy(int value,
                                           long delaySeconds,
                                           SpendingBudgetValidator spendingBudgetValidator) {
        spendingBudgetValidator.validate(value, delaySeconds);

        this.value = value;
        this.delaySeconds = delaySeconds;
    }

    @Override
    public boolean isAvailableToExecute() {
        return nextStart.isAfter(LocalDateTime.now());
    }
}
