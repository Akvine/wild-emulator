package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.converters.ApiCardConverter;
import ru.akvine.wild.emulator.api.rest.meta.ApiCardControllerMeta;
import ru.akvine.wild.emulator.api.services.ApiAuthenticationService;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.services.card.CardService;

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
}
