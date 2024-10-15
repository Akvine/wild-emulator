package ru.akvine.wild.emulator.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akvine.wild.emulator.core.repositories.entities.AdvertStatisticEntity;

public interface AdvertStatisticRepository extends JpaRepository<AdvertStatisticEntity, Long> {
}
