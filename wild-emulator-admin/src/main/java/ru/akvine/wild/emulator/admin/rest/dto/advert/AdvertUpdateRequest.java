package ru.akvine.wild.emulator.admin.rest.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertUpdateRequest {
    private String name;
    private Integer budget;
    private Integer cpm;
    private String status;
    private String type;
    private AdvertBudgetStrategyDto strategyInfo;
}
