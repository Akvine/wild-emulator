package ru.akvine.wild.emulator.admin.rest.converters.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.card.CardCreateRequest;
import ru.akvine.wild.emulator.admin.rest.dto.card.CardDto;
import ru.akvine.wild.emulator.admin.rest.dto.card.CardListResponse;
import ru.akvine.wild.emulator.admin.utils.SecurityHelper;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.services.dto.card.CardCreate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardConverter {
    private final SecurityHelper securityHelper;

    public CardCreate convertToCardCreate(CardCreateRequest request) {
        return new CardCreate()
                .setBarcode(request.getBarcode())
                .setName(request.getName())
                .setType(request.getType())
                .setCategoryUuid(request.getCategoryUuid())
                .setClientUuid(securityHelper.getCurrentUser().getUuid())
                .setPrice(request.getPrice())
                .setDiscount(request.getDiscount());
    }

    public CardListResponse convertToCardListResponse(List<CardModel> cards) {
        return new CardListResponse().setCards(cards.stream().map(this::buildCardDto).toList());
    }

    private CardDto buildCardDto(CardModel card) {
        return new CardDto()
                .setUuid(card.getUuid())
                .setBarcode(card.getBarcode())
                .setName(card.getName())
                .setType(card.getCardType().getType())
                .setCategoryUuid(card.getCardCategory().getUuid())
                .setPrice(card.getPrice())
                .setDiscount(card.getDiscount());
    }
}
