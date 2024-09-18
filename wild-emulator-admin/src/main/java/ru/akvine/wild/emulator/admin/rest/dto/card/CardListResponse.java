package ru.akvine.wild.emulator.admin.rest.dto.card;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardListResponse extends SuccessfulResponse {
    private List<CardDto> cards;
}
