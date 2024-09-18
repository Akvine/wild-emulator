package ru.akvine.wild.emulator.admin.rest.impl.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.card.CardCategoryConverter;
import ru.akvine.wild.emulator.admin.rest.meta.card.CardCategoryControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.card.CardCategoryModel;
import ru.akvine.wild.emulator.core.services.card.CardCategoryService;

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
