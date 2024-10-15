package ru.akvine.wild.emulator.core.providers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.strategy.budget.factory.SpendingBudgetStrategyFactory;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SpendingBudgetFactoryProvider {
    private final Map<SpendingBudgetType, SpendingBudgetStrategyFactory> factories;
}
