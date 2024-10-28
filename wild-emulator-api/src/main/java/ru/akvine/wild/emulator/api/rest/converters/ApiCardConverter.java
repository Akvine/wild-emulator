package ru.akvine.wild.emulator.api.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.api.rest.dto.card.*;
import ru.akvine.wild.emulator.api.rest.dto.card.good.*;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.services.dto.card.CardChangePriceAndDiscount;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiCardConverter {

    public ApiCardListResponse convertToApiCardListResponse(List<CardModel> cards) {
        return new ApiCardListResponse().setCards(cards.stream().map(this::buildApiCardDto).toList());
    }

    public List<CardChangePriceAndDiscount> convertToCardChangePriceAndDiscount(AccessTokenModel accessToken,
                                                                                SetGoodPriceAndDiscountRequest request) {
        return request
                .getData()
                .stream()
                .map(setGood -> new CardChangePriceAndDiscount()
                        .setUuid(setGood.getNmID())
                        .setClientId(accessToken.getClient().getId())
                        .setDiscount(setGood.getDiscount())
                        .setPrice(setGood.getPrice()))
                .toList();
    }

    public ApiGetGoodResponse convertToApiGetGoodResponse(CardModel card) {
        // TODO: переделать ApiGoodDto и ApiGoodSizeDto на инициаилазцию через конструктор
        return new ApiGetGoodResponse()
                .setData(new ApiGoodsData()
                        .setListGoods(List.of(
                                new ApiGoodDto()
                                        .setNmId(card.getUuid())
                                        .setDiscount(card.getDiscount())
                                        .setSizes(List.of(
                                                new ApiGoodSizeDto()
                                                        .setPrice(card.getPrice())
                                                        .setDiscountedPrice(card.calculateDiscountedPrice())
                                        ))
                        )));
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
