package ru.akvine.wild.emulator.core.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    @Query("from CardEntity ce where ce.deleted = false")
    @NotNull
    @Deprecated
    List<CardEntity> findAll();

    @Query("from CardEntity ce where ce.uuid = :uuid and ce.deleted = false")
    Optional<CardEntity> findByUuid(@Param("uuid") int uuid);

    @Query("from CardEntity ce join ce.client cec where cec.deleted = false and cec.id = :id")
    List<CardEntity> findAll(@Param("id") long clientId);
}
