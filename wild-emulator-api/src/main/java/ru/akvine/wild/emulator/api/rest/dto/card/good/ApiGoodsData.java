package ru.akvine.wild.emulator.api.rest.dto.card.good;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ApiGoodsData {
    private List<ApiGoodDto> listGoods;
}
