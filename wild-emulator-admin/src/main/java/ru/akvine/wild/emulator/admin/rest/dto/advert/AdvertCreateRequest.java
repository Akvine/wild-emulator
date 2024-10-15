package ru.akvine.wild.emulator.admin.rest.dto.advert;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertCreateRequest {
    private int cardUuid;

    @NotBlank
    private String name;

    @Min(0)
    private int budget;

    @Min(0)
    private int cpm;

    @NotNull
    private AdvertBudgetStrategyDto strategyInfo;
}
