package ru.akvine.wild.emulator.api.rest.dto.card.good;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SetGoodDto {
    private int nmID;
    private int price;
    private int discount;
}
