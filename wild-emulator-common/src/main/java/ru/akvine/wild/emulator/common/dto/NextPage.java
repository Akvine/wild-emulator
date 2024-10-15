package ru.akvine.wild.emulator.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NextPage {
    @Min(0)
    private int page;

    @Min(0)
    @Max(100)
    private int count;
}
