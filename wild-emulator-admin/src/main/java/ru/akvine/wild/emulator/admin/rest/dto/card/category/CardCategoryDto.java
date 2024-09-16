package ru.akvine.wild.emulator.admin.rest.dto.card.category;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardCategoryDto {
    private Integer uuid;
    private String name;
}
