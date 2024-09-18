package ru.akvine.wild.emulator.core.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    @Query("from CardEntity ce where ce.deleted = false")
    @NotNull
    List<CardEntity> findAll();
}
