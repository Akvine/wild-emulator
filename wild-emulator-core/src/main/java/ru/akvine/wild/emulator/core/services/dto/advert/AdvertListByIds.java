package ru.akvine.wild.emulator.core.services.dto.advert;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertListByIds {
    private List<Integer> ids;
    private long clientId;
}
