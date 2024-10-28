package ru.akvine.wild.emulator.core.services.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardChangePriceAndDiscount {
    private int uuid;
    private long clientId;
    private int price;
    private int discount;
}
