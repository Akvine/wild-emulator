package ru.akvine.wild.emulator.core.services.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardUpdate {
    private int uuid;
    private long clientId;
    private String name;
    private String barcode;
    private Integer price;
    private Integer discount;
}
