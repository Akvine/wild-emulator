package ru.akvine.wild.emulator.core.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.repositories.entities.base.SoftBaseEntity;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
//@Table(name = "ADVERT_ENTITY")
//@Entity
public class AdvertEntity extends SoftBaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertEntitySeq")
    @SequenceGenerator(name = "advertEntitySeq", sequenceName = "SEQ_ADVERT_ENTITY", allocationSize = 1000)
    private Long id;

    @Column(name = "UUID", nullable = false)
    @NotNull
    private int uuid;

    @Column(name = "TITLE", nullable = false)
    @NotNull
    private String title;

    @NotNull
    @Column(name = "CHANGE_TIME", nullable = false)
    private Date changeTime;

    @Column(name = "STATUS", nullable = false)
    private int ordinalStatus;

    @Column(name = "ORDINAL_TYPE", nullable = false)
    private int ordinalType;

    @Column(name = "CPM", nullable = false)
    private int cpm;

    @Column(name = "BUDGET_SUM", nullable = false)
    private Integer budgetSum;

    @OneToOne
    @JoinColumn(name = "CARD_ID", nullable = false)
    private CardEntity card;
}
