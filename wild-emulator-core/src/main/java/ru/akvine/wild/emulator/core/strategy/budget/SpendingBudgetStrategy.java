package ru.akvine.wild.emulator.core.strategy.budget;

public interface SpendingBudgetStrategy {
    int execute(int currentBudget);

    boolean isAvailableToExecute();
}
