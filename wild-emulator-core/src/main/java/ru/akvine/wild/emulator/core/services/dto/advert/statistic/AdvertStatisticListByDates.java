package ru.akvine.wild.emulator.core.services.dto.advert.statistic;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class AdvertStatisticListByDates {
    private long clientId;
    private int advertUuid;
    private List<LocalDate> dates;
}
