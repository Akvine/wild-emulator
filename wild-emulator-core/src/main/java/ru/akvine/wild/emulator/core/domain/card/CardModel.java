package ru.akvine.wild.emulator.core.domain.card;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.ClientModel;
import ru.akvine.wild.emulator.core.domain.base.SoftModel;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;

@Data
@Accessors(chain = true)
public class CardModel extends SoftModel {
    private Long id;
    private Integer uuid;
    private String name;
    private String barcode;
    private int price;
    private int discount;
    private CardCategoryModel cardCategory;
    private CardTypeModel cardType;
    private ClientModel clientModel;

    public CardModel(CardEntity cardEntity) {
        this.id = cardEntity.getId();
        this.uuid = cardEntity.getUuid();
        this.name = cardEntity.getBarcode();
        this.price = cardEntity.getPrice();
        this.discount = cardEntity.getDiscount();
        this.barcode = cardEntity.getBarcode();
        this.cardCategory = new CardCategoryModel(cardEntity.getCardCategory());
        this.cardType = new CardTypeModel(cardEntity.getCardType());
        this.clientModel = new ClientModel(cardEntity.getClient());

        this.createdDate = cardEntity.getCreatedDate();
        this.updatedDate = cardEntity.getUpdatedDate();
        this.deletedDate = cardEntity.getDeletedDate();
        this.deleted = cardEntity.isDeleted();
    }

    public double calculateDiscountedPrice() {
        return price * (1 - ((double) discount / 100));
    }
}
