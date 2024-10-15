package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertDto {
    private int cardUuid;
    private String name;
    private String status;
    private String type;
    private int cpm;
    private int budget;
    private StrategyBudgetCreateResponse strategyBudget;
}
