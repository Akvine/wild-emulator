package ru.akvine.wild.emulator.core.services.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CardChangeDiscount {
    private int uuid;
    private long clientId;
    private int discount;
}
