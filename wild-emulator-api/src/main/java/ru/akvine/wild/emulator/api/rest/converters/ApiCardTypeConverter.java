package ru.akvine.wild.emulator.api.rest.converters;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.CardTypeListResponse;
import ru.akvine.wild.emulator.core.domain.CardTypeModel;

import java.util.List;

@Component
public class ApiCardTypeConverter {
    public CardTypeListResponse convertToCardTypeListResponse(List<CardTypeModel> cardTypeModels) {
        Preconditions.checkNotNull(cardTypeModels, "cardTypeModels is null");
        return new CardTypeListResponse().setData(cardTypeModels.stream().map(CardTypeModel::getType).toList());
    }
}
