package ru.akvine.wild.emulator.core.strategy.budget.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.strategy.budget.ConstantSpendingBudgetStrategy;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetValidator;

@Component
@RequiredArgsConstructor
public class ConstantSpendingBudgetStrategyFactory implements SpendingBudgetStrategyFactory {
    private final SpendingBudgetValidator spendingBudgetValidator;

    @Override
    public SpendingBudgetStrategy create(int value, long delaySeconds) {
        return new ConstantSpendingBudgetStrategy(value, delaySeconds, spendingBudgetValidator);
    }

    @Override
    public SpendingBudgetType getType() {
        return SpendingBudgetType.CONSTANT;
    }
}
