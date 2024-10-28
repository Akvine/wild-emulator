package ru.akvine.wild.emulator.core.services.card;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.common.utils.RandomCodeGenerator;
import ru.akvine.wild.emulator.core.constants.UUIDConstants;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.exceptions.CardNotFoundException;
import ru.akvine.wild.emulator.core.repositories.CardRepository;
import ru.akvine.wild.emulator.core.repositories.entities.CardCategoryEntity;
import ru.akvine.wild.emulator.core.repositories.entities.CardEntity;
import ru.akvine.wild.emulator.core.repositories.entities.CardTypeEntity;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;
import ru.akvine.wild.emulator.core.services.ClientService;
import ru.akvine.wild.emulator.core.services.dto.card.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardCategoryService cardCategoryService;
    private final CardTypeService cardTypeService;
    private final ClientService clientService;

    public List<CardModel> list(long clientId) {
        return cardRepository
                .findAll(clientId)
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
                .setUuid(RandomCodeGenerator.generateNewRandomNumericCode(UUIDConstants.CARD_UUID_LENGTH))
                .setName(cardCreate.getName())
                .setBarcode(cardCreate.getBarcode())
                .setPrice(cardCreate.getPrice())
                .setDiscount(cardCreate.getDiscount())
                .setCardType(cardType)
                .setCardCategory(cardCategory)
                .setClient(client);

        return new CardModel(cardRepository.save(cardToSave));
    }

    public CardModel update(CardUpdate cardUpdate) {
        Preconditions.checkNotNull(cardUpdate, "cardUpdate is null");

        CardEntity cardToUpdate = verifyExistsByUuid(cardUpdate.getUuid(), cardUpdate.getClientId());
        if (StringUtils.isNotBlank(cardUpdate.getName()) &&
                !cardToUpdate.getName().equals(cardUpdate.getName())) {
            cardToUpdate.setName(cardUpdate.getName());
        }
        if (StringUtils.isNotBlank(cardUpdate.getBarcode()) &&
                !cardToUpdate.getBarcode().equals(cardUpdate.getBarcode())) {
            cardToUpdate.setBarcode(cardUpdate.getBarcode());
        }
        if (cardUpdate.getDiscount() != null &&
                cardToUpdate.getDiscount() != cardUpdate.getDiscount()) {
            cardToUpdate.setDiscount(cardUpdate.getDiscount());
        }
        if (cardUpdate.getPrice() != null &&
                cardToUpdate.getPrice() != cardUpdate.getPrice()) {
            cardToUpdate.setPrice(cardUpdate.getPrice());
        }

        return new CardModel(cardRepository.save(cardToUpdate));
    }

    public CardModel getByUuid(int uuid, long clientId) {
        return new CardModel(verifyExistsByUuid(uuid, clientId));
    }

    public CardEntity verifyExistsByUuid(int uuid, long clientId) {
        return cardRepository
                .findByUuid(uuid, clientId)
                .orElseThrow(() -> new CardNotFoundException("Card with uuid = [" + uuid + "] not found!"));
    }

    public CardModel changePriceAndDiscount(CardChangePriceAndDiscount changePriceAndDiscount) {
        Preconditions.checkNotNull(changePriceAndDiscount, "changePriceAndDiscount is null");
        CardChangePrice changePrice = new CardChangePrice()
                .setUuid(changePriceAndDiscount.getUuid())
                .setClientId(changePriceAndDiscount.getClientId())
                .setPrice(changePriceAndDiscount.getPrice());
        changePrice(changePrice);

        CardChangeDiscount changeDiscount = new CardChangeDiscount()
                .setUuid(changePrice.getUuid())
                .setClientId(changePrice.getClientId())
                .setDiscount(changePriceAndDiscount.getDiscount());
        return changeDiscount(changeDiscount);
    }

    public CardModel changePrice(CardChangePrice changePrice) {
        Preconditions.checkNotNull(changePrice, "changePrice is null");
        CardUpdate cardUpdate = new CardUpdate()
                .setUuid(changePrice.getUuid())
                .setClientId(changePrice.getClientId())
                .setPrice(changePrice.getPrice());
        return update(cardUpdate);
    }

    public CardModel changeDiscount(CardChangeDiscount changeDiscount) {
        Preconditions.checkNotNull(changeDiscount, "changeDiscount is null");
        CardUpdate cardUpdate = new CardUpdate()
                .setUuid(changeDiscount.getUuid())
                .setClientId(changeDiscount.getClientId())
                .setDiscount(changeDiscount.getDiscount());
        return update(cardUpdate);
    }
}
