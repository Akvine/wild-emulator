package ru.akvine.wild.emulator.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertStatisticEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AdvertStatisticRepository extends JpaRepository<AdvertStatisticEntity, Long> {
    @Query("from AdvertStatisticEntity ase where " +
            "ase.deleted = false " +
            "and " +
            "ase.advert.card.client = :clientId " +
            "and " +
            "ase.createdDate in :createdDates")
    List<AdvertStatisticEntity> listByDates(@Param("clientId") long clientId,
                                            @Param("createdDates") Collection<LocalDate> createdDates);
}
