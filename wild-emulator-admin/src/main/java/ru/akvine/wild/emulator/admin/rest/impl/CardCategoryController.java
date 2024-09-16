package ru.akvine.wild.emulator.admin.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.CardCategoryConverter;
import ru.akvine.wild.emulator.admin.rest.meta.CardCategoryControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.CardCategoryModel;
import ru.akvine.wild.emulator.core.services.CardCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardCategoryController implements CardCategoryControllerMeta {
    private final CardCategoryService cardCategoryService;
    private final CardCategoryConverter cardCategoryConverter;

    @Override
    public Response list() {
        List<CardCategoryModel> cardCategories = cardCategoryService.list();
        return cardCategoryConverter.convertToCardCategoryListResponse(cardCategories);
    }
}
