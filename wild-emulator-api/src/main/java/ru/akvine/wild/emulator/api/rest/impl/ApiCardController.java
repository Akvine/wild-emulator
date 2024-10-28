package ru.akvine.wild.emulator.api.rest.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.converters.ApiCardConverter;
import ru.akvine.wild.emulator.api.rest.dto.card.good.SetGoodPriceAndDiscountRequest;
import ru.akvine.wild.emulator.api.rest.meta.ApiCardControllerMeta;
import ru.akvine.wild.emulator.api.services.ApiAuthenticationService;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.services.card.CardService;
import ru.akvine.wild.emulator.core.services.dto.card.CardChangePriceAndDiscount;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiCardController implements ApiCardControllerMeta {
    private final ApiAuthenticationService apiAuthenticationService;
    private final CardService cardService;
    private final ApiCardConverter apiCardConverter;

    @Override
    public Response list(@RequestHeader("Authorization") String token) {
        AccessTokenModel tokenModel = apiAuthenticationService.checkToken(token);
        List<CardModel> cards = cardService.list(tokenModel.getClient().getId());
        return apiCardConverter.convertToApiCardListResponse(cards);
    }

    @Override
    public Response listGoods(@RequestHeader("Authorization") String token,
                              @RequestParam("filterNmID") int uuid) {
        AccessTokenModel accessToken = apiAuthenticationService.checkToken(token);
        long clientId = accessToken.getClient().getId();
        CardModel card = cardService.getByUuid(uuid, clientId);
        return apiCardConverter.convertToApiGetGoodResponse(card);
    }

    @Override
    public Response setGoodsAndDiscount(@RequestHeader("Authorization") String token,
                                        @RequestBody @Valid SetGoodPriceAndDiscountRequest request) {
        AccessTokenModel accessToken = apiAuthenticationService.checkToken(token);
        List<CardChangePriceAndDiscount> changePriceAndDiscount = apiCardConverter.convertToCardChangePriceAndDiscount(accessToken, request);
        changePriceAndDiscount.forEach(cardService::changePriceAndDiscount);
        return new SuccessfulResponse();
    }
}
