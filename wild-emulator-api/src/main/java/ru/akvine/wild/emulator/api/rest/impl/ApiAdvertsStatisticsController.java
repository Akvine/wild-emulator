package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.dto.adverts.statistics.AdvertFullStatisticDatesDto;
import ru.akvine.wild.emulator.api.rest.meta.ApiAdvertsStatisticsControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.services.AdvertStatisticService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiAdvertsStatisticsController  implements ApiAdvertsStatisticsControllerMeta {
    private final AdvertStatisticService advertStatisticService;

    @Override
    public Response getFullByDates(String token, List<AdvertFullStatisticDatesDto> statisticsRequest) {
        return null;
    }
}
