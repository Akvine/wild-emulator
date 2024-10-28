package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertCreate {
    private int cardUuid;
    private long clientId;
    private String name;
    private int cpm;
    private int budgetSum;
    private AdvertBudgetSpendingCreate advertBudgetSpendingCreate;
}
