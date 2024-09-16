package ru.akvine.wild.emulator.admin.rest.dto.card.type;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardTypeListResponse extends SuccessfulResponse {
    private List<CardTypeDto> types;
}
