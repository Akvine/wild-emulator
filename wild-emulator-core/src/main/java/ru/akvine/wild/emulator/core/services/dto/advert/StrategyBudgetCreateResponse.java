package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StrategyBudgetCreateResponse {
    private int value;
    private long delaySeconds;
    private String type;
}
