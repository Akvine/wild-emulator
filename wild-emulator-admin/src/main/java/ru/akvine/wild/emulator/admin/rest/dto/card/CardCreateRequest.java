package ru.akvine.wild.emulator.admin.rest.dto.card;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardCreateRequest {
    private int categoryUuid;

    @NotBlank
    private String type;

    @NotBlank
    private String name;

    @NotBlank
    private String barcode;

    @Min(1)
    @Max(10000000)
    private int price;

    @Min(0)
    @Max(100)
    private int discount;
}
