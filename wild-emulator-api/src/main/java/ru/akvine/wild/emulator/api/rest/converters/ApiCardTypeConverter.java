package ru.akvine.wild.emulator.api.rest.converters;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.card.type.ApiCardTypeListResponse;
import ru.akvine.wild.emulator.core.domain.card.CardTypeModel;

import java.util.List;

@Component
public class ApiCardTypeConverter {
    public ApiCardTypeListResponse convertToCardTypeListResponse(List<CardTypeModel> cardTypeModels) {
        Preconditions.checkNotNull(cardTypeModels, "cardTypeModels is null");
        return new ApiCardTypeListResponse().setData(cardTypeModels.stream().map(CardTypeModel::getType).toList());
    }
}
