package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertCountResponse extends SuccessfulResponse {
    private int all;
    private List<AdvertStatisticDto> adverts;
}
