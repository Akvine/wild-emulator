package ru.akvine.wild.emulator.admin.rest.converters.advert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertCreateRequest;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertListResponse;
import ru.akvine.wild.emulator.admin.rest.dto.advert.AdvertUpdateRequest;
import ru.akvine.wild.emulator.admin.utils.SecurityHelper;
import ru.akvine.wild.emulator.common.dto.NextPage;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.enums.AdvertStatus;
import ru.akvine.wild.emulator.core.enums.AdvertType;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.services.dto.advert.*;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdvertConverter {
    private final SecurityHelper securityHelper;

    public AdvertList convertToAdvertList(NextPage nextPage) {
        long id = securityHelper.getCurrentUser().getId();
        return new AdvertList()
                .setClientId(id)
                .setCount(nextPage.getCount())
                .setPages(nextPage.getPage());
    }

    public AdvertCreate convertToAdvertCreate(AdvertCreateRequest advertCreateRequest) {
        return new AdvertCreate()
                .setCardUuid(advertCreateRequest.getCardUuid())
                .setName(advertCreateRequest.getName())
                .setCpm(advertCreateRequest.getCpm())
                .setBudgetSum(advertCreateRequest.getBudget())
                .setAdvertBudgetSpendingCreate(new AdvertBudgetSpendingCreate()
                        .setValue(advertCreateRequest.getStrategyInfo().getValue())
                        .setDelaySeconds(advertCreateRequest.getStrategyInfo().getDelaySeconds())
                        .setType(SpendingBudgetType.safeValueOf(
                                advertCreateRequest.getStrategyInfo().getType())));
    }

    public AdvertUpdate convertToAdvertUpdate(AdvertUpdateRequest request) {
        return new AdvertUpdate()
                .setName(request.getName())
                .setCpm(request.getCpm())
                .setBudget(request.getBudget());
    }

    public AdvertListResponse convertToConvertAdvertListResponse(List<AdvertModel> adverts) {
        return new AdvertListResponse().setAdverts(adverts.stream().map(this::buildAdvertDto).toList());
    }

    private AdvertDto buildAdvertDto(AdvertModel advert) {
        return new AdvertDto()
                .setCardUuid(advert.getCard().getUuid())
                .setName(advert.getName())
                .setType(AdvertType.getByCode(advert.getType()).name())
                .setStatus(AdvertStatus.getByCode(advert.getStatus()).name())
                .setBudget(advert.getCurrentBudget())
                .setCpm(advert.getCpm())
                .setStrategyBudget(new StrategyBudgetCreateResponse()
                        .setType(advert.getAdvertBudgetSpendingModel().getType().name())
                        .setValue(advert.getAdvertBudgetSpendingModel().getValue())
                        .setDelaySeconds(advert.getAdvertBudgetSpendingModel().getDelaySeconds()));
    }
}
