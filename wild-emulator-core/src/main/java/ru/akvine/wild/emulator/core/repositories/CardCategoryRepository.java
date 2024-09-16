package ru.akvine.wild.emulator.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;

public interface CardCategoryRepository extends JpaRepository<CardCategoryEntity, Long> {
}
