package ru.akvine.wild.emulator.core.domain.card;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.base.Model;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;

@Data
@Accessors(chain = true)
public class CardCategoryModel extends Model {
    private Long id;
    private Integer uuid;
    private String name;

    public CardCategoryModel(CardCategoryEntity cardCategoryEntity) {
        this.id = cardCategoryEntity.getId();
        this.uuid = cardCategoryEntity.getUuid();
        this.name = cardCategoryEntity.getName();

        this.createdDate = cardCategoryEntity.getCreatedDate();
        this.updatedDate = cardCategoryEntity.getUpdatedDate();
    }
}
