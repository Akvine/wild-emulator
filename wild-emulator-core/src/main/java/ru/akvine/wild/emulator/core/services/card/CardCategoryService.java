package ru.akvine.wild.emulator.core.services.card;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.card.CardCategoryModel;
import ru.akvine.wild.emulator.core.exceptions.CardCategoryNotFoundException;
import ru.akvine.wild.emulator.core.repositories.CardCategoryRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;

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

    public CardCategoryEntity verifyExistsByUuid(Integer uuid) {
        Preconditions.checkNotNull(uuid, "uuid is null");
        return cardCategoryRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new CardCategoryNotFoundException("Card category with uuid = [" + uuid + "] not found!"));
    }
}
