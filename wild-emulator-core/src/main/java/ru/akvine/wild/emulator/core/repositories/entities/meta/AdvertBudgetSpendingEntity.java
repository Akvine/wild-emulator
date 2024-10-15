package ru.akvine.wild.emulator.core.repositories.entities.meta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.enums.SpendingBudgetType;
import ru.akvine.wild.emulator.core.repositories.entities.base.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "ADVERT_BUDGET_SPENDING_ENTITY")
public class AdvertBudgetSpendingEntity extends BaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertBudgetSpendingEntitySeq")
    @SequenceGenerator(name = "advertBudgetSpendingEntitySeq", sequenceName = "SEQ_ADVERT_BUDGET_SPENDING_ENTITY", allocationSize = 1000)
    private Long id;

    @Column(name = "VALUE", nullable = false)
    private int value;

    @Column(name = "DELAY_SECONDS", nullable = false)
    private long delaySeconds;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpendingBudgetType type;
}
