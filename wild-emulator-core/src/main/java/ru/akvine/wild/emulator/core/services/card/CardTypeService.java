package ru.akvine.wild.emulator.core.services.card;

import com.google.common.base.Preconditions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.card.CardTypeModel;
import ru.akvine.wild.emulator.core.exceptions.CardTypeNotFoundException;
import ru.akvine.wild.emulator.core.repositories.CardTypeRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardTypeEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardTypeService {
    private final Map<String, Optional<CardTypeEntity>> simpleCardTypeCache = new HashMap<>();
    private final CardTypeRepository cardTypeRepository;

    @PostConstruct
    private void init() {
        List<CardTypeEntity> allCardTypes = cardTypeRepository.findAll();
        for (CardTypeEntity cardType : allCardTypes) {
            simpleCardTypeCache.put(cardType.getType(), Optional.of(cardType));
        }
    }

    public List<CardTypeModel> list() {
        return simpleCardTypeCache
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(cardTypeOptional -> new CardTypeModel(cardTypeOptional.get()))
                .toList();
    }

    public CardTypeEntity verifyExistsByType(String type) {
        Preconditions.checkNotNull(type, "type is null");
        Optional<CardTypeEntity> cardTypeEntity = simpleCardTypeCache.get(type);
        return cardTypeEntity.orElseGet(() -> cardTypeRepository
                .findByType(type)
                .orElseThrow(() -> new CardTypeNotFoundException("Card with type = [" + type + "] not found!")));
    }

}
