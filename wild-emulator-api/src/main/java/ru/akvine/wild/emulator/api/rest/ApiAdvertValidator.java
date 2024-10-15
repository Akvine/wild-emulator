package ru.akvine.wild.emulator.api.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.constants.ApiErrorConstants;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertDepositRequest;
import ru.akvine.wild.emulator.core.exceptions.validation.ValidationException;

@Component
public class ApiAdvertValidator {
    @Value("${advert.min.budget.sum}")
    private int minBudgetDepositSum;

    public void verifyAdvertDepositRequest(AdvertDepositRequest request) {
        if (request.getSum() < minBudgetDepositSum) {
            String errorMessage = String.format(
                    "Sum can't be less than minimum = [%s] for depositing",
                    request.getSum());
            throw new ValidationException(
                    ApiErrorConstants.Validation.DEPOSIT_SUM_ERROR,
                    errorMessage
            );
        }
    }
}
