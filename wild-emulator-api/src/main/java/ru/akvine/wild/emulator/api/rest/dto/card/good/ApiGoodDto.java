package ru.akvine.wild.emulator.api.rest.dto.card.good;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ApiGoodDto {
    private int nmId;
    private int discount;
    private List<ApiGoodSizeDto> sizes;
}
