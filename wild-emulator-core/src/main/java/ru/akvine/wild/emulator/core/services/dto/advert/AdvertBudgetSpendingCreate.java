package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;

@Data
@Accessors(chain = true)
public class AdvertBudgetSpendingCreate {
    private int value;
    private long delaySeconds;
    private SpendingBudgetType type;
}
