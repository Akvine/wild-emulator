package ru.akvine.wild.emulator.core.domain.meta;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.domain.base.Model;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.repositories.entities.meta.AdvertBudgetSpendingEntity;

@Data
@Accessors(chain = true)
public class AdvertBudgetSpendingModel extends Model {
    private Long id;
    private int value;
    private long delaySeconds;
    private SpendingBudgetType type;

    public AdvertBudgetSpendingModel(AdvertBudgetSpendingEntity advertBudgetSpending) {
        this.id = advertBudgetSpending.getId();
        this.value = advertBudgetSpending.getValue();
        this.delaySeconds = advertBudgetSpending.getDelaySeconds();
        this.type = advertBudgetSpending.getType();

        this.createdDate = advertBudgetSpending.getCreatedDate();
        this.updatedDate = advertBudgetSpending.getUpdatedDate();
    }
}
