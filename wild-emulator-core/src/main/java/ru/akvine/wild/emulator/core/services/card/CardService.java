package ru.akvine.wild.emulator.core.services.card;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.common.utils.RandomCodeGenerator;
import ru.akvine.wild.emulator.core.constants.UUIDConstants;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.repositories.CardRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;
import ru.akvine.wild.emulator.core.repositories.entities.CardTypeEntity;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;
import ru.akvine.wild.emulator.core.services.ClientService;
import ru.akvine.wild.emulator.core.services.dto.card.CardCreate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardCategoryService cardCategoryService;
    private final CardTypeService cardTypeService;
    private final ClientService clientService;

    public List<CardModel> list() {
        return cardRepository
                .findAll()
                .stream()
                .map(CardModel::new)
                .toList();
    }

    public CardModel create(CardCreate cardCreate) {
        Preconditions.checkNotNull(cardCreate, "cardCreate is null");

        ClientEntity client = clientService.verifyExistsByUuid(cardCreate.getClientUuid());
        CardTypeEntity cardType = cardTypeService.verifyExistsByType(cardCreate.getType());
        CardCategoryEntity cardCategory = cardCategoryService.verifyExistsByUuid(cardCreate.getCategoryUuid());

        CardEntity cardToSave = new CardEntity()
                .setUuid(Integer.parseInt(
                        RandomCodeGenerator.generateNewRandomNumericCode(UUIDConstants.CARD_UUID_LENGTH)))
                .setName(cardCreate.getName())
                .setBarcode(cardCreate.getBarcode())
                .setPrice(cardCreate.getPrice())
                .setDiscount(cardCreate.getDiscount())
                .setCardType(cardType)
                .setCardCategory(cardCategory)
                .setClient(client);

        return new CardModel(cardRepository.save(cardToSave));
    }
}
