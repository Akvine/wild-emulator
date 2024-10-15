package ru.akvine.wild.emulator.core.strategy.budget.factory;

import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;

public interface SpendingBudgetStrategyFactory {
    SpendingBudgetStrategy create(int value, long delaySeconds);

    SpendingBudgetType getType();
}
