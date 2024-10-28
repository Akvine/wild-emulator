package ru.akvine.wild.emulator.core.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<AdvertEntity, Long> {
    @Query("from AdvertEntity ae where ae.status = :status and ae.deleted = false")
    List<AdvertEntity> findByStatus(@Param("status") int status);

    @Query("from AdvertEntity ae where ae.uuid = :uuid and ae.deleted = false")
    Optional<AdvertEntity> findByUuid(@Param("uuid") int uuid);

    @Query("from AdvertEntity ae where ae.card.client.id = :clientId and ae.deleted = false")
    List<AdvertEntity> list(@Param("clientId") long clientId,
                            @NotNull Pageable pageable);

    @Query("from AdvertEntity ae where ae.card.client.id = :clientId and ae.deleted = false and ae.id in :ids")
    List<AdvertEntity> list(@Param("clientId") long clientId,
                            @Param("ids") Collection<Integer> ids);

    @Query("from AdvertEntity ae where ae.card.client.id = :clientId and ae.deleted = false")
    List<AdvertEntity> list(@Param("clientId") long clientId);
}
