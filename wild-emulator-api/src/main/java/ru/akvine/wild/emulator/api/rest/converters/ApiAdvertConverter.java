package ru.akvine.wild.emulator.api.rest.converters;

import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.adverts.*;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertDeposit;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertListByIds;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertRename;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertUpdate;

import java.util.List;

@Component
public class ApiAdvertConverter {

    public AdvertUpdate convertToAdvertUpdate(AdvertChangeCpmRequest request) {
        return new AdvertUpdate()
                .setUuid(request.getAdvertId())
                .setCpm(request.getCpm());
    }

    public AdvertListByIds convertToAdvertListByIds(AccessTokenModel accessToken,
                                                    List<Integer> advertIds) {
        return new AdvertListByIds()
                .setClientId(accessToken.getClient().getId())
                .setIds(advertIds);
    }

    public AdvertListResponse convertToAdvertListResponse(List<AdvertModel> adverts) {
        return new AdvertListResponse().setAdverts(adverts.stream().map(this::buildAdvertDto).toList());
    }

    private AdvertDto buildAdvertDto(AdvertModel advert) {
        return new AdvertDto()
                .setAdvertId(advert.getUuid())
                .setType(advert.getType())
                .setStatus(advert.getStatus())
                .setChangeTime(advert.getChangeTime())
                .setAdvertParams(new AdvertParams()
                        .setNms(List.of(
                                advert.getCard().getUuid()
                        ))
                        .setCpm(advert.getCpm())
                        .setSubject(new AdvertSubject()));
    }

    public AdvertDeposit convertToAdvertDeposit(int uuid, AdvertDepositRequest request) {
        return new AdvertDeposit()
                .setSum(request.getSum())
                .setUuid(uuid);
    }

    public AdvertRename convertToAdvertRename(AdvertRenameRequest request) {
        return new AdvertRename()
                .setName(request.getName())
                .setUuid(request.getAdvertId());
    }

    public AdvertDepositResponse convertToAdvertDepositResponse(AdvertModel advert) {
        return new AdvertDepositResponse().setTotal(advert.getCurrentBudget());
    }

    public AdvertBudgetInfoResponse convertToAdvertBudgetInfoResponse(AdvertModel advert) {
        return new AdvertBudgetInfoResponse().setTotal(advert.getCurrentBudget());
    }
}
