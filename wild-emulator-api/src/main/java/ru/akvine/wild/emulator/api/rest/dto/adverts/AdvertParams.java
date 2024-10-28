package ru.akvine.wild.emulator.api.rest.dto.adverts;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertParams {
    private AdvertSubject subject;
    private int cpm;
    private List<Integer> nms;
}
