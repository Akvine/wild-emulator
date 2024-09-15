package ru.akvine.wild.emulator.admin.rest.dto.token;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

@Data
@Accessors(chain = true)
public class TokenGenerateResponse extends SuccessfulResponse {
    @NotBlank
    private String token;
}
