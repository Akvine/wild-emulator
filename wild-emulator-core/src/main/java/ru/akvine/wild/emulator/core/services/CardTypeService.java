package ru.akvine.wild.emulator.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.CardTypeModel;
import ru.akvine.wild.emulator.core.repositories.CardTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeService {
    private final CardTypeRepository cardTypeRepository;

    public List<CardTypeModel> list() {
        return cardTypeRepository
                .findAll()
                .stream()
                .map(CardTypeModel::new)
                .toList();
    }
}
