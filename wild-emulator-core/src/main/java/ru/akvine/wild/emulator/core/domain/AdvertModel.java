package ru.akvine.wild.emulator.core.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.base.SoftModel;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.domain.meta.AdvertBudgetSpendingModel;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertEntity;

@Data
@Accessors(chain = true)
public class AdvertModel extends SoftModel {
    private Long id;
    private int uuid;
    private String name;
    private int currentBudget;
    private int cpm;
    private int status;
    private int type;
    private CardModel card;
    private AdvertBudgetSpendingModel advertBudgetSpendingModel;

    public AdvertModel(AdvertEntity advert) {
        this.id = advert.getId();
        this.uuid = advert.getUuid();
        this.name = advert.getName();
        this.currentBudget = advert.getBudgetSum();
        this.cpm = advert.getCpm();
        this.status = advert.getStatus();
        this.type = advert.getType();
        this.card = new CardModel(advert.getCard());
        this.advertBudgetSpendingModel = new AdvertBudgetSpendingModel(advert.getAdvertBudgetSpending());

        this.createdDate = advert.getCreatedDate();
        this.updatedDate = advert.getUpdatedDate();
        this.deletedDate = advert.getDeletedDate();
        this.deleted = advert.isDeleted();
    }
}
