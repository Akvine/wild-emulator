package ru.akvine.wild.emulator.core.services;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.domain.AdvertStatisticModel;
import ru.akvine.wild.emulator.core.repositories.AdvertStatisticRepository;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertEntity;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertStatisticEntity;
import ru.akvine.wild.emulator.core.services.dto.advert.statistic.AdvertStatisticListByDates;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdvertStatisticService {
    private final AdvertService advertService;
    private final AdvertStatisticRepository advertStatisticRepository;

    private final static Random random = new Random();

    public AdvertStatisticModel generate(AdvertModel advert) {
        Preconditions.checkNotNull(advert, "advert is null");
        AdvertEntity advertEntity = advertService.verifyExistsByUuid(advert.getUuid());

        AdvertStatisticEntity advertStatisticToSave = new AdvertStatisticEntity()
                .setViews(random.nextInt(100))
                .setClicks(random.nextInt(500))
                .setCtr(random.nextDouble(10))
                .setCpc(random.nextDouble(10))
                .setSum(random.nextInt(1000))
                .setAtbs(random.nextInt(100))
                .setOrders(random.nextInt(1000))
                .setCr(random.nextInt(10))
                .setShks(random.nextInt(50))
                .setSumPrice(random.nextInt(1000))
                .setAdvertEntity(advertEntity);

        return new AdvertStatisticModel(advertStatisticRepository.save(advertStatisticToSave));
    }

    public List<AdvertStatisticModel> list(AdvertStatisticListByDates listByDates) {
        Preconditions.checkNotNull(listByDates, "advertStatisticListByDates is null");
        return advertStatisticRepository
                .listByDates(
                        listByDates.getClientId(),
                        listByDates.getDates())
                .stream()
                .map(AdvertStatisticModel::new)
                .toList();
    }
}
