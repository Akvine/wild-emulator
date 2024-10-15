package ru.akvine.wild.emulator.core.services;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.strategy.budget.SpendingBudgetStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AdvertBudgetStrategyService {
    private final static Map<Integer, SpendingBudgetStrategy> launchedBudget = new ConcurrentHashMap<>();

    public void put(int advertUuid, SpendingBudgetStrategy strategyToPut) {
        Preconditions.checkNotNull(strategyToPut, "strategyToPut is null");

        if (launchedBudget.containsKey(advertUuid)) {
            launchedBudget.put(advertUuid, strategyToPut);
        } else {
            String errorMessage = String.format(
                    "Strategy for advert with uuid = [%s] is already executes. Can't put!", advertUuid
            );
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Nullable
    public SpendingBudgetStrategy get(int advertUuid) {
        return launchedBudget.get(advertUuid);
    }

    public void remove(int advertUuid) {
        if (launchedBudget.containsKey(advertUuid)) {
            launchedBudget.remove(advertUuid);
        } else {
            String errorMessage = String.format(
                    "Strategy for advert with uuid = [%s] is already executes. Can't remove!", advertUuid
            );
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
