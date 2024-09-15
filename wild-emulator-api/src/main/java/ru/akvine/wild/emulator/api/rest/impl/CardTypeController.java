package ru.akvine.wild.emulator.api.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.api.rest.converters.CardTypeConverter;
import ru.akvine.wild.emulator.api.rest.meta.CardTypeControllerMeta;
import ru.akvine.wild.emulator.api.services.ApiAuthenticationService;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.CardTypeModel;
import ru.akvine.wild.emulator.core.services.CardTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardTypeController implements CardTypeControllerMeta {
    private final ApiAuthenticationService apiAuthenticationService;
    private final CardTypeService cardTypeService;
    private final CardTypeConverter cardTypeConverter;

    @Override
    public Response list(@RequestHeader("Authorization") String token) {
        apiAuthenticationService.checkToken(token);
        List<CardTypeModel> cardTypes = cardTypeService.list();
        return cardTypeConverter.convertToCardTypeListResponse(cardTypes);
    }
}
