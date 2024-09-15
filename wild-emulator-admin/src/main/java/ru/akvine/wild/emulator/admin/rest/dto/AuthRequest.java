package ru.akvine.wild.emulator.admin.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRequest {
    @ToString.Exclude
    @NotBlank
    private String email;

    @ToString.Exclude
    @NotBlank
    private String password;
}
