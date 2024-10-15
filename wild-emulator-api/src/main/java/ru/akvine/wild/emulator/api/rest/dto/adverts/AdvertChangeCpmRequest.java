package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertChangeCpmRequest {
    private int advertId;
    private int type;
    private int cpm;
    private int param;
}
