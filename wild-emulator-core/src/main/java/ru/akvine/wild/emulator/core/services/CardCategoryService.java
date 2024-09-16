package ru.akvine.wild.emulator.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.CardCategoryModel;
import ru.akvine.wild.emulator.core.repositories.CardCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardCategoryService {
    private final CardCategoryRepository cardCategoryRepository;

    public List<CardCategoryModel> list() {
        return cardCategoryRepository
                .findAll()
                .stream()
                .map(CardCategoryModel::new)
                .toList();
    }
}
