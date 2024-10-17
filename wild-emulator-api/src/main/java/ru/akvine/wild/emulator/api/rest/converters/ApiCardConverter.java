package ru.akvine.wild.emulator.api.rest.converters;

import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.card.ApiCardCharacteristic;
import ru.akvine.wild.emulator.api.rest.dto.card.ApiCardDto;
import ru.akvine.wild.emulator.api.rest.dto.card.ApiCardListResponse;
import ru.akvine.wild.emulator.api.rest.dto.card.ApiSizeDto;
import ru.akvine.wild.emulator.core.domain.card.CardModel;

import java.util.Date;
import java.util.List;

@Component
public class ApiCardConverter {
    public ApiCardListResponse convertToApiCardListResponse(List<CardModel> cards) {
        return new ApiCardListResponse().setCards(cards.stream().map(this::buildApiCardDto).toList());
    }

    private ApiCardDto buildApiCardDto(CardModel card) {
        return new ApiCardDto()
                .setTitle(card.getName())
                .setUpdatedAt(card.getUpdatedDate() == null ? new Date().toString() : card.getUpdatedDate().toString())
                .setSizes(List.of(
                        new ApiSizeDto().setSkus(List.of(card.getBarcode()))
                ))
                .setSubjectID(card.getCardCategory().getUuid())
                .setSubjectName(card.getCardCategory().getName())
                .setCharacteristics(List.of(
                        new ApiCardCharacteristic()
                                .setName("Пол")
                                .setValue(List.of(card.getCardType().getType()))
                ));
    }
}
