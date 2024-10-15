package ru.akvine.wild.emulator.core.validators;

import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.core.constants.ErrorConstants;
import ru.akvine.wild.emulator.core.enums.AdvertStatus;
import ru.akvine.wild.emulator.core.exceptions.validation.ValidationException;

@Component
public class AdvertStatusValidator implements Validator<String> {
    @Override
    public void validate(String value) {
        try {
            AdvertStatus.getByValue(value);
        } catch (Exception exception) {
            throw new ValidationException(ErrorConstants.ADVERT_STATUS_INVALID_ERROR, exception.getMessage());
        }
    }
}
