package ru.akvine.wild.emulator.core.strategy.budget;


public class SummingSpendingBudgetStrategy extends CommonSpendingBudgetStrategy {
    private int iterations = 1;

    public SummingSpendingBudgetStrategy(int value,
                                         long seconds,
                                         SpendingBudgetValidator spendingBudgetValidator) {
        super(value, seconds, spendingBudgetValidator);
    }

    @Override
    public int execute(int currentBudget) {
        int difference = currentBudget - value * iterations;
        iterations += 1;
        nextStart = nextStart.plusSeconds(delaySeconds);
        return Math.max(difference, 0);
    }
}
