package ru.akvine.wild.emulator.core.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.enums.AdvertStatus;
import ru.akvine.wild.emulator.core.repositories.entities.base.SoftBaseEntity;
import ru.akvine.wild.emulator.core.repositories.entities.meta.AdvertBudgetSpendingEntity;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "ADVERT_ENTITY")
@Entity
public class AdvertEntity extends SoftBaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertEntitySeq")
    @SequenceGenerator(name = "advertEntitySeq", sequenceName = "SEQ_ADVERT_ENTITY", allocationSize = 1000)
    private Long id;

    @Column(name = "UUID", nullable = false)
    @NotNull
    private int uuid;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @Column(name = "CHANGE_TIME", nullable = false)
    private Date changeTime;

    @Column(name = "STATUS", nullable = false)
    private int status;

    @Column(name = "TYPE", nullable = false)
    private int type;

    @Column(name = "CPM", nullable = false)
    private int cpm;

    @Column(name = "BUDGET_SUM", nullable = false)
    private int budgetSum;

    @OneToOne
    @JoinColumn(name = "ADVERT_BUDGET_SPENDING_ID", nullable = false)
    @NotNull
    private AdvertBudgetSpendingEntity advertBudgetSpending;

    @OneToOne
    @JoinColumn(name = "CARD_ID", nullable = false)
    @NotNull
    private CardEntity card;

    @Transient
    public boolean isRunning() {
        return status == AdvertStatus.RUNNING.getCode();
    }
}
