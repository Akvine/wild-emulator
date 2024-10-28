package ru.akvine.wild.emulator.api.rest.dto.adverts.statistics;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertFullStatisticDatesDto {
    private int id;
    private List<String> dates;
}
