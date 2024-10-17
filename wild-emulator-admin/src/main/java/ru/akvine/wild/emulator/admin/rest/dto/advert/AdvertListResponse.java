package ru.akvine.wild.emulator.admin.rest.dto.advert;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertDto;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AdvertListResponse extends SuccessfulResponse {
    @NotNull
    private List<@Valid AdvertDto> adverts;
}
