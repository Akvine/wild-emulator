package ru.akvine.wild.emulator.admin.rest.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardDto {
    private int uuid;
    private String name;
    private String type;
    private int categoryUuid;
    private String barcode;
    private int price;
    private int discount;
}
