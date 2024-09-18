package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.converters.ApiCardTypeConverter;
import ru.akvine.wild.emulator.api.rest.meta.ApiCardTypeControllerMeta;
import ru.akvine.wild.emulator.api.services.ApiAuthenticationService;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.card.CardTypeModel;
import ru.akvine.wild.emulator.core.services.card.CardTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiCardTypeController implements ApiCardTypeControllerMeta {
    private final ApiAuthenticationService apiAuthenticationService;
    private final CardTypeService cardTypeService;
    private final ApiCardTypeConverter apiCardTypeConverter;

    @Override
    public Response list(@RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        List<CardTypeModel> cardTypes = cardTypeService.list();
        return apiCardTypeConverter.convertToCardTypeListResponse(cardTypes);
    }
}
