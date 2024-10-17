package ru.akvine.wild.emulator.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.providers.SpendingBudgetFactoryProvider;
import ru.akvine.wild.emulator.core.strategy.budget.factory.SpendingBudgetStrategyFactory;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Configuration
public class ProvidersConfig {

    @Bean
    public SpendingBudgetFactoryProvider spendingBudgetFactoryProvider(List<SpendingBudgetStrategyFactory> factoryList) {
        Map<SpendingBudgetType, SpendingBudgetStrategyFactory> factories = factoryList
                .stream()
                .collect(toMap(SpendingBudgetStrategyFactory::getType, identity()));
        return new SpendingBudgetFactoryProvider(factories);
    }
}
