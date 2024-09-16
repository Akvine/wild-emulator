package ru.akvine.wild.emulator.admin.rest.dto.card.category;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardCategoryListResponse extends SuccessfulResponse {
    private List<CardCategoryDto> categories;
}
