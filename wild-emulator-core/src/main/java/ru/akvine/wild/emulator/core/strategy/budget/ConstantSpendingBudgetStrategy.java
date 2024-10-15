package ru.akvine.wild.emulator.core.strategy.budget;

public class ConstantSpendingBudgetStrategy extends CommonSpendingBudgetStrategy {

    public ConstantSpendingBudgetStrategy(int value,
                                          long delaySeconds,
                                          SpendingBudgetValidator spendingBudgetValidator) {
        super(value, delaySeconds, spendingBudgetValidator);
    }

    @Override
    public int execute(int currentBudget) {
        int difference = currentBudget - value;
        nextStart = nextStart.plusSeconds(delaySeconds);
        return Math.max(difference, 0);
    }

    @Override
    public boolean isAvailableToExecute() {
        return super.isAvailableToExecute();
    }
}
