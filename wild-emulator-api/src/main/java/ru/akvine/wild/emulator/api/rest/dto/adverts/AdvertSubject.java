package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdvertSubject {
    private String name;
    private int id;
}
