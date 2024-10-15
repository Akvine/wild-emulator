package ru.akvine.wild.emulator.core.job;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.enums.AdvertStatus;
import ru.akvine.wild.emulator.core.services.AdvertBudgetStrategyService;
import ru.akvine.wild.emulator.core.services.AdvertService;
import ru.akvine.wild.emulator.core.services.AdvertStatisticService;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertUpdate;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SpendingBudgetJob {
    private final AdvertService advertService;
    private final AdvertStatisticService advertStatisticService;
    private final AdvertBudgetStrategyService advertBudgetStrategyService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void spend() {
        List<AdvertModel> runningAdverts = advertService.getByStatus(AdvertStatus.RUNNING.getCode());
        for (AdvertModel runningAdvert : runningAdverts) {
            SpendingBudgetStrategy spendingBudgetStrategy = advertBudgetStrategyService.get(runningAdvert.getUuid());
            if (spendingBudgetStrategy != null && spendingBudgetStrategy.isAvailableToExecute()) {
                int currentBudget = runningAdvert.getCurrentBudget();
                int difference = spendingBudgetStrategy.execute(currentBudget);
                if (difference == 0) {
                    advertStatisticService.generate(runningAdvert);
                    runningAdvert.setStatus(AdvertStatus.PAUSE.getCode());
                    advertBudgetStrategyService.remove(runningAdvert.getUuid());
                }
                runningAdvert.setCurrentBudget(difference);
                AdvertUpdate advertUpdate = new AdvertUpdate()
                        .setUuid(runningAdvert.getUuid())
                        .setStatus(AdvertStatus.getByCode(runningAdvert.getStatus()).name())
                        .setBudget(difference);
                advertService.update(advertUpdate);
            }
        }
    }
}
