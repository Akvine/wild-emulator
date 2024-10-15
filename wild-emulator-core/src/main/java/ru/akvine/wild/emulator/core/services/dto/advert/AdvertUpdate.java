package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertUpdate {
    private int uuid;
    private String name;
    private Integer cpm;
    private Integer budget;
    private String status;
}
