package ru.akvine.wild.emulator.admin.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.CardTypeConverter;
import ru.akvine.wild.emulator.admin.rest.meta.CardTypeControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.CardTypeModel;
import ru.akvine.wild.emulator.core.services.CardTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardTypeController implements CardTypeControllerMeta {
    private final CardTypeService cardTypeService;
    private final CardTypeConverter cardTypeConverter;

    @Override
    public Response list() {
        List<CardTypeModel> cardTypes = cardTypeService.list();
        return cardTypeConverter.convertToCardTypeListResponse(cardTypes);
    }
}
