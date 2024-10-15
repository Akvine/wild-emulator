package ru.akvine.wild.emulator.admin.rest.validators;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertCreateRequest;
import ru.akvine.wild.emulator.core.validators.SpendingBudgetTypeValidator;

@Component
@RequiredArgsConstructor
public class AdvertValidator {
    private final SpendingBudgetTypeValidator spendingBudgetTypeValidator;

    public void verifyAdvertCreateRequest(AdvertCreateRequest request) {
        Preconditions.checkNotNull(request, "advertCreateRequest is null");
        spendingBudgetTypeValidator.validate(request.getStrategyInfo().getType());
    }
}
