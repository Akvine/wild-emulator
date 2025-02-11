package ru.akvine.wild.emulator.core.repositories.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.core.repositories.entities.base.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "CARD_CATEGORY_ENTITY")
public class CardCategoryEntity extends BaseEntity {
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardCategoryEntitySeq")
    @SequenceGenerator(name = "cardCategoryEntitySeq", sequenceName = "SEQ_CARD_CATEGORY_ENTITY", allocationSize = 1000)
    @NotNull
    private Long id;

    @Column(name = "UUID", nullable = false)
    private int uuid;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;
}
