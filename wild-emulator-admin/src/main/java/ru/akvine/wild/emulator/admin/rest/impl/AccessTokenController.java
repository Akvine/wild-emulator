package ru.akvine.wild.emulator.admin.rest.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.wild.emulator.admin.rest.converters.AccessTokenConverter;
import ru.akvine.wild.emulator.admin.rest.dto.token.TokenGenerateRequest;
import ru.akvine.wild.emulator.admin.rest.meta.AccessTokenControllerMeta;
import ru.akvine.wild.emulator.common.dto.Response;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.services.security.AccessTokenService;
import ru.akvine.wild.emulator.core.services.token.TokenGenerate;

@RestController
@RequiredArgsConstructor
public class AccessTokenController implements AccessTokenControllerMeta {
    private final AccessTokenService accessTokenService;
    private final AccessTokenConverter accessTokenConverter;

    @Override
    public Response generate(@RequestBody @Valid TokenGenerateRequest request) {
        TokenGenerate tokenGenerate = accessTokenConverter.convertToTokenGenerate(request);
        AccessTokenModel accessToken = accessTokenService.generate(tokenGenerate);
        return accessTokenConverter.convertToTokenGenerateResponse(accessToken);
    }
}
