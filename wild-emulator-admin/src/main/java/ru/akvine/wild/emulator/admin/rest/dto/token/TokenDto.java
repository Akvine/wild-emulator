package ru.akvine.wild.emulator.admin.rest.dto.token;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TokenDto {
    @NotBlank
    private String token;
}
