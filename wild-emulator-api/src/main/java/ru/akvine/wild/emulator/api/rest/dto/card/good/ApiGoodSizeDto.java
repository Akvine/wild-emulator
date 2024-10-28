package ru.akvine.wild.emulator.api.rest.dto.card.good;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiGoodSizeDto {
    private int price;
    private double discountedPrice;
}
