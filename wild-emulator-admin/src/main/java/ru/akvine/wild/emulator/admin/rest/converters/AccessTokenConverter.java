package ru.akvine.wild.emulator.admin.rest.converters;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akvine.wild.emulator.admin.rest.dto.token.TokenGenerateRequest;
import ru.akvine.wild.emulator.admin.rest.dto.token.TokenGenerateResponse;
import ru.akvine.wild.emulator.admin.utils.SecurityHelper;
import ru.akvine.wild.emulator.common.utils.DateUtils;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.services.token.TokenGenerate;

@Component
@RequiredArgsConstructor
public class AccessTokenConverter {
    private final SecurityHelper securityHelper;

    public TokenGenerate convertToTokenGenerate(TokenGenerateRequest request) {
        Preconditions.checkNotNull(request, "TokenGenerateRequest is null");
        return new TokenGenerate()
                .setClientUuid(securityHelper.getCurrentUser().getUuid())
                .setExpiredAt(request.getExpiredDate() == null ? null : DateUtils.convertToLocalDateTime(request.getExpiredDate()));
    }

    public TokenGenerateResponse convertToTokenGenerateResponse(AccessTokenModel accessTokenModel) {
        Preconditions.checkNotNull(accessTokenModel, "accessTokenModel is null");
        return new TokenGenerateResponse().setToken(accessTokenModel.getToken());
    }
}
