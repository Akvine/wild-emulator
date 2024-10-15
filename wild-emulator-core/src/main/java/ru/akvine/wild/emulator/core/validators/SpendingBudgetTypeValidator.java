package ru.akvine.wild.emulator.core.validators;

import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.core.constants.ErrorConstants;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.exceptions.validation.ValidationException;

@Component
public class SpendingBudgetTypeValidator implements Validator<String> {
    @Override
    public void validate(String type) {
        try {
            SpendingBudgetType.safeValueOf(type);
        } catch (Exception exception) {
            throw new ValidationException(
                    ErrorConstants.BUDGET_STRATEGY_TYPE_INVALID_ERROR,
                    exception.getMessage());
        }
    }
}
