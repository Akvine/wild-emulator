package ru.akvine.wild.emulator.admin.rest.converters.card;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.card.type.CardTypeDto;
import ru.akvine.wild.emulator.admin.rest.dto.card.type.CardTypeListResponse;
import ru.akvine.wild.emulator.core.domain.card.CardTypeModel;

import java.util.List;

@Component
public class CardTypeConverter {
    public CardTypeListResponse convertToCardTypeListResponse(List<CardTypeModel> cardTypes) {
        Preconditions.checkNotNull(cardTypes, "cardTypes is null");
        return new CardTypeListResponse().setTypes(cardTypes.stream().map(this::convertToCardTypeDto).toList());
    }

    private CardTypeDto convertToCardTypeDto(CardTypeModel cardType) {
        return new CardTypeDto().setType(cardType.getType());
    }
}
