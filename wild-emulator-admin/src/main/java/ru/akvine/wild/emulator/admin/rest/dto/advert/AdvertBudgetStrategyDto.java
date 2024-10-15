package ru.akvine.wild.emulator.admin.rest.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertBudgetStrategyDto {
    private int value;
    private long delaySeconds;
    private String type;
}
