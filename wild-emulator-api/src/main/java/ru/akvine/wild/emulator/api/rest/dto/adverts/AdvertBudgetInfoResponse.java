package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

@Data
@Accessors(chain = true)
public class AdvertBudgetInfoResponse extends SuccessfulResponse {
    private int total;
}
