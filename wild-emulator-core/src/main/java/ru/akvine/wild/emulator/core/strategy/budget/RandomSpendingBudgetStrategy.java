package ru.akvine.wild.emulator.core.strategy.budget;

import java.util.Random;

public class RandomSpendingBudgetStrategy extends CommonSpendingBudgetStrategy {
    private final Random random;

    public RandomSpendingBudgetStrategy(int value,
                                           long delaySeconds,
                                           SpendingBudgetValidator spendingBudgetValidator) {
        super(value, delaySeconds, spendingBudgetValidator);
        random = new Random();
    }

    @Override
    public int execute(int currentBudget) {
        int difference = currentBudget - random.nextInt(value);
        nextStart = nextStart.plusSeconds(delaySeconds);
        return Math.max(difference, 0);
    }
}
