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
@Table(name = "CARD_ENTITY")
public class CardEntity extends SoftBaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardEntitySeq")
    @SequenceGenerator(name = "cardEntitySeq", sequenceName = "SEQ_CARD_ENTITY", allocationSize = 1000)
    private Long id;

    @Column(name = "UUID", nullable = false)
    private int uuid;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "BARCODE", nullable = false)
    private String barcode;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "DISCOUNT", nullable = false)
    private int discount;

    @ManyToOne
    @JoinColumn(name = "CARD_CATEGORY_ID", nullable = false)
    private CardCategoryEntity cardCategory;

    @ManyToOne
    @JoinColumn(name = "CARD_TYPE_ID", nullable = false)
    private CardTypeEntity cardType;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity client;

    @Transient
    public double calculateDiscountedPrice() {
        return price * (1 - ((double) discount / 100));
    }
}
