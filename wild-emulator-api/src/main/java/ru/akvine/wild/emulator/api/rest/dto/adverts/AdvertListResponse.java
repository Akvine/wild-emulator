package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertListResponse extends SuccessfulResponse {
    private List<AdvertDto> adverts;
}
