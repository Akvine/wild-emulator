package ru.akvine.wild.emulator.admin.rest.meta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.wild.emulator.admin.rest.dto.token.TokenGenerateRequest;
import ru.akvine.wild.emulator.common.dto.Response;

@RequestMapping(value = "/access/tokens")
public interface AccessTokenControllerMeta {
    @PostMapping(value = "/generate")
    Response generate(@RequestBody @Valid TokenGenerateRequest request);
}
