package ru.akvine.wild.emulator.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;

import java.util.Optional;

public interface CardCategoryRepository extends JpaRepository<CardCategoryEntity, Long> {
    @Query("from CardCategoryEntity cce where cce.uuid = :uuid")
    Optional<CardCategoryEntity> findByUuid(@Param("uuid") int uuid);
}
