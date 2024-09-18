package ru.akvine.wild.emulator.core.services.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardCreate {
    private int categoryUuid;
    private String type;
    private int price;
    private int discount;
    private String barcode;
    private String name;
    private String clientUuid;
}
