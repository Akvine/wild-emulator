package ru.akvine.wild.emulator.api.rest.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class CardTypeListResponse extends SuccessfulResponse {
    private List<String> data;
    private String error;
    private String errorText;
}
