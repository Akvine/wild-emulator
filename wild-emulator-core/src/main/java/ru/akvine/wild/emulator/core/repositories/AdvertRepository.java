package ru.akvine.wild.emulator.core.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertEntity;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<AdvertEntity, Long> {
    @Query("from AdvertEntity ae where ae.status = :status and ae.deleted = false")
    List<AdvertEntity> findByStatus(@Param("status") int status);

    @Query("from AdvertEntity ae where ae.uuid = :uuid and ae.deleted = false")
    Optional<AdvertEntity> findByUuid(@Param("uuid") int uuid);

    @Query("from AdvertEntity ae join card aec join client aecc where aecc.deleted = false and aecc.id = :clientId")
    List<AdvertEntity> list(@Param("clientId") long clientId,
                            @NotNull Pageable pageable);
}
