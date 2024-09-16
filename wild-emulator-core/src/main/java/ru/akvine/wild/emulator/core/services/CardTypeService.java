package ru.akvine.wild.emulator.core.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.CardTypeModel;
import ru.akvine.wild.emulator.core.repositories.CardTypeRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardTypeEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardTypeService {
    private final Map<String, CardTypeEntity> simpleCardTypeCache = new HashMap<>();
    private final CardTypeRepository cardTypeRepository;

    @PostConstruct
    private void init() {
        List<CardTypeEntity> allCardTypes = cardTypeRepository.findAll();
        for (CardTypeEntity cardType : allCardTypes) {
            simpleCardTypeCache.put(cardType.getType(), cardType);
        }
    }

    public List<CardTypeModel> list() {
        return simpleCardTypeCache
                .values()
                .stream()
                .map(CardTypeModel::new)
                .toList();
    }
}
