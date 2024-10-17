package ru.akvine.wild.emulator.admin.rest.impl.card;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.card.CardConverter;
import ru.akvine.wild.emulator.admin.rest.dto.card.CardCreateRequest;
import ru.akvine.wild.emulator.admin.rest.meta.card.CardControllerMeta;
import ru.akvine.wild.emulator.admin.utils.SecurityHelper;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.card.CardModel;
import ru.akvine.wild.emulator.core.services.card.CardService;
import ru.akvine.wild.emulator.core.services.dto.card.CardCreate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController implements CardControllerMeta {
    private final CardService cardService;
    private final CardConverter cardConverter;
    private final SecurityHelper securityHelper;

    @Override
    public Response list() {
        long currentUserId = securityHelper.getCurrentUser().getId();
        List<CardModel> cards = cardService.list(currentUserId);
        return cardConverter.convertToCardListResponse(cards);
    }

    @Override
    public Response create(@RequestBody @Valid CardCreateRequest request) {
        CardCreate cardCreate = cardConverter.convertToCardCreate(request);
        CardModel card = cardService.create(cardCreate);
        return cardConverter.convertToCardListResponse(List.of(card));
    }
}
