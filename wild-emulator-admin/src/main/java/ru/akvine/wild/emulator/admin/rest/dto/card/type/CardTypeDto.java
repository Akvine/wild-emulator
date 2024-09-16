package ru.akvine.wild.emulator.admin.rest.dto.card.type;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardTypeDto {
    @NotBlank
    private String type;
}
