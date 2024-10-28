package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.ApiAdvertValidator;
import ru.akvine.wild.emulator.api.rest.converters.ApiAdvertConverter;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertChangeCpmRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertDepositRequest;
import ru.akvine.wild.emulator.api.rest.dto.adverts.AdvertRenameRequest;
import ru.akvine.wild.emulator.api.rest.meta.ApiAdvertsControllerMeta;
import ru.akvine.wild.emulator.api.services.ApiAuthenticationService;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.domain.AdvertModel;
import ru.akvine.wild.emulator.core.services.AdvertService;
import ru.akvine.wild.emulator.core.services.dto.advert.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiAdvertsController implements ApiAdvertsControllerMeta {
    private final ApiAuthenticationService apiAuthenticationService;
    private final ApiAdvertConverter advertConverter;
    private final AdvertService advertService;
    private final ApiAdvertValidator advertValidator;

    @Override
    public Response budget(int uuid,
                           @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        AdvertModel advertModel = advertService.getByUuid(uuid);
        return advertConverter.convertToAdvertBudgetInfoResponse(advertModel);
    }

    @Override
    public Response start(int uuid,
                          @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        advertService.run(uuid);
        return new SuccessfulResponse();
    }

    @Override
    public Response rename(AdvertRenameRequest request,
                           @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        AdvertRename advertRename = advertConverter.convertToAdvertRename(request);
        advertService.rename(advertRename);
        return new SuccessfulResponse();
    }

    @Override
    public Response pause(int uuid,
                          @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        advertService.pause(uuid);
        return new SuccessfulResponse();
    }

    @Override
    public Response cpm(AdvertChangeCpmRequest request,
                        @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        AdvertUpdate advertUpdate = advertConverter.convertToAdvertUpdate(request);
        advertService.update(advertUpdate);
        return new SuccessfulResponse();
    }

    @Override
    public Response deposit(int uuid,
                            AdvertDepositRequest request,
                            @RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        advertValidator.verifyAdvertDepositRequest(request);
        AdvertDeposit advertDeposit = advertConverter.convertToAdvertDeposit(uuid, request);
        AdvertModel advertModel = advertService.deposit(advertDeposit);
        return advertConverter.convertToAdvertDepositResponse(advertModel);
    }

    @Override
    public Response count(@RequestHeader("Authorization") String token) {
        AccessTokenModel accessToken = apiAuthenticationService.checkToken(token);
        Map<Integer, Map<Integer, GroupedAdverts>> groupedAdverts = advertService.listAndGroupBy(accessToken.getClient().getId());
        return null;
    }

    @Override
    public Response list(@RequestHeader("Authorization") String token,
                         List<Integer> advertIds) {
        AccessTokenModel accessToken = apiAuthenticationService.checkToken(token);
        AdvertListByIds advertListByIds = advertConverter.convertToAdvertListByIds(accessToken, advertIds);
        List<AdvertModel> adverts = advertService.list(advertListByIds);
        return advertConverter.convertToAdvertListResponse(adverts);
    }
}
