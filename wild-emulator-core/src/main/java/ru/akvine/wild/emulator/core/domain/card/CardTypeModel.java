package ru.akvine.wild.emulator.core.domain.card;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.base.Model;
import ru.akvine.wild.emulator.core.repositories.entities.CardTypeEntity;

@Data
@Accessors(chain = true)
public class CardTypeModel extends Model {
    private Long id;
    private String type;

    public CardTypeModel(CardTypeEntity cardTypeEntity) {
        this.id = cardTypeEntity.getId();
        this.type = cardTypeEntity.getType();

        this.createdDate = cardTypeEntity.getCreatedDate();
        this.updatedDate = cardTypeEntity.getUpdatedDate();
    }
}
