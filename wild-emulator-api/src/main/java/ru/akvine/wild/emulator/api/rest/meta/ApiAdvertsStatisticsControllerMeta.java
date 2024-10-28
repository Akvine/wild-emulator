package ru.akvine.wild.emulator.api.rest.meta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.api.rest.dto.adverts.statistics.AdvertFullStatisticDatesDto;
import ru.akvine.wild.emulator.common.dto.Response;

import java.util.List;

@RequestMapping(value = "/api/adverts/statistics")
public interface ApiAdvertsStatisticsControllerMeta {
    @PostMapping(value = "/v2/fullstats")
    Response getFullByDates(@RequestHeader("Authorization") String token,
                            List<AdvertFullStatisticDatesDto> statisticsRequest);
}
