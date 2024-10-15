package ru.akvine.wild.emulator.core.repositories.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.repositories.entities.base.SoftBaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "ADVERT_STATISTIC_ENTITY")
public class AdvertStatisticEntity extends SoftBaseEntity {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertStatisticEntitySequence")
    @SequenceGenerator(name = "advertStatisticEntitySequence", sequenceName = "SEQ_ADVERT_STATISTIC_ENTITY", allocationSize = 1000)
    private Long id;

    @Column(name = "VIEWS")
    private int views;

    @Column(name = "CLICKS")
    private int clicks;

    @Column(name = "CTR")
    private double ctr;

    @Column(name = "CPC")
    private double cpc;

    @Column(name = "SUM")
    private int sum;

    @Column(name = "ATBS")
    private int atbs;

    @Column(name = "ORDERS")
    private int orders;

    @Column(name = "CR")
    private int cr;

    @Column(name = "SHKS")
    private int shks;

    @Column(name = "SUM_PRICE")
    private int sumPrice;

    @ManyToOne
    @JoinColumn(name = "ADVERT_ID", nullable = false)
    private AdvertEntity advertEntity;
}
