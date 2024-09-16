package ru.akvine.wild.emulator.admin.rest.converters;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.card.category.CardCategoryDto;
import ru.akvine.wild.emulator.admin.rest.dto.card.category.CardCategoryListResponse;
import ru.akvine.wild.emulator.core.domain.CardCategoryModel;

import java.util.List;

@Component
public class CardCategoryConverter {
    public CardCategoryListResponse convertToCardCategoryListResponse(List<CardCategoryModel> cardCategories) {
        Preconditions.checkNotNull(cardCategories, "cardCategories is null");
        return new CardCategoryListResponse()
                .setCategories(cardCategories.stream().map(this::buildCardCategoryDto).toList());
    }

    private CardCategoryDto buildCardCategoryDto(CardCategoryModel cardCategory) {
        return new CardCategoryDto()
                .setName(cardCategory.getName())
                .setUuid(cardCategory.getUuid());
    }
}
