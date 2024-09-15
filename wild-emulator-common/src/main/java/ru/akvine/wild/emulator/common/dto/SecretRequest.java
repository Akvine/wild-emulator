package ru.akvine.wild.emulator.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SecretRequest {
    @NotBlank
    @ToString.Exclude
    private String secret;
}
