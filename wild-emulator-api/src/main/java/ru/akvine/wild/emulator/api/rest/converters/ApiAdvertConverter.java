package ru.akvine.wild.emulator.api.rest.converters;

import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.adverts.*;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertDeposit;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertRename;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertUpdate;

@Component
public class ApiAdvertConverter {
    public AdvertUpdate convertToAdvertUpdate(AdvertChangeCpmRequest request) {
        return new AdvertUpdate()
                .setUuid(request.getAdvertId())
                .setCpm(request.getCpm());
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
