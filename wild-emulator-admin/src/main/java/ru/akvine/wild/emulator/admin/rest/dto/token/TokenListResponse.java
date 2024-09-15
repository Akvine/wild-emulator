package ru.akvine.wild.emulator.admin.rest.dto.token;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.akvine.wild.emulator.common.dto.SuccessfulResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class TokenListResponse extends SuccessfulResponse {
    private List<TokenDto> tokens;
}
