package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.ApiAdvertValidator;
import ru.akvine.wild.emulator.api.rest.converters.ApiAdvertConverter;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertChangeCpmRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertDepositRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertRenameRequest;
import ru.akvine.wild.emulator.api.rest.meta.ApiAdvertsControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.services.AdvertService;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertDeposit;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertRename;
import ru.akvine.wild.emulator.core.services.dto.advert.AdvertUpdate;

@RestController
@RequiredArgsConstructor
public class ApiAdvertsController implements ApiAdvertsControllerMeta {
    private final ApiAdvertConverter advertConverter;
    private final AdvertService advertService;
    private final ApiAdvertValidator advertValidator;

    @Override
    public Response budget(int uuid) {
        AdvertModel advertModel = advertService.getByUuid(uuid);
        return advertConverter.convertToAdvertBudgetInfoResponse(advertModel);
    }

    @Override
    public Response start(int uuid) {
        advertService.run(uuid);
        return new SuccessfulResponse();
    }

    @Override
    public Response rename(AdvertRenameRequest request) {
        AdvertRename advertRename = advertConverter.convertToAdvertRename(request);
        advertService.rename(advertRename);
        return new SuccessfulResponse();
    }

    @Override
    public Response pause(int uuid) {
        advertService.pause(uuid);
        return new SuccessfulResponse();
    }

    @Override
    public Response cpm(AdvertChangeCpmRequest request) {
        AdvertUpdate advertUpdate = advertConverter.convertToAdvertUpdate(request);
        advertService.update(advertUpdate);
        return new SuccessfulResponse();
    }

    @Override
    public Response deposit(int uuid, AdvertDepositRequest request) {
        advertValidator.verifyAdvertDepositRequest(request);
        AdvertDeposit advertDeposit = advertConverter.convertToAdvertDeposit(uuid, request);
        AdvertModel advertModel = advertService.deposit(advertDeposit);
        return advertConverter.convertToAdvertDepositResponse(advertModel);
    }
}
