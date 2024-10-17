package ru.akvine.wild.emulator.api.rest.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ApiSizeDto {
    private List<String> skus;
}
