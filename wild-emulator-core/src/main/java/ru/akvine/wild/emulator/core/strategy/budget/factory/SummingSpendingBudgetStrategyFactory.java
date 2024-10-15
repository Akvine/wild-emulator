package ru.akvine.wild.emulator.core.strategy.budget.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.strategy.budget.SummingSpendingBudgetStrategy;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetValidator;

@Component
@RequiredArgsConstructor
public class SummingSpendingBudgetStrategyFactory implements SpendingBudgetStrategyFactory {
    private final SpendingBudgetValidator spendingBudgetValidator;

    @Override
    public SpendingBudgetStrategy create(int value, long delaySeconds) {
        return new SummingSpendingBudgetStrategy(value, delaySeconds, spendingBudgetValidator);
    }

    @Override
    public SpendingBudgetType getType() {
        return SpendingBudgetType.SUMMING;
    }
}
