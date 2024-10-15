package ru.akvine.wild.emulator.api.rest.dto.adverts;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertRenameRequest {
    @Min(0)
    private int advertId;

    @NotBlank
    private String name;
}
