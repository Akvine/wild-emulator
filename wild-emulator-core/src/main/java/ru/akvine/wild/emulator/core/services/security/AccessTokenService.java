package ru.akvine.wild.emulator.core.services.security;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.common.utils.UUIDGenerator;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.repositories.AccessTokenRepository;
import ru.akvine.wild.emulator.core.repositories.entities.AccessTokenEntity;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;
import ru.akvine.wild.emulator.core.services.ClientService;
import ru.akvine.wild.emulator.core.services.dto.token.TokenGenerate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessTokenService {
    private final AccessTokenRepository accessTokenRepository;
    private final ClientService clientService;

    @Value("${default.expired.days.count}")
    private int defaultExpiredDaysCount;

    public AccessTokenModel generate(TokenGenerate tokenGenerate) {
        Preconditions.checkNotNull(tokenGenerate, "TokenGenerate is null");

        ClientEntity client = clientService.verifyExistsByUuid(tokenGenerate.getClientUuid());
        Optional<AccessTokenEntity> token = accessTokenRepository.findByClientId(client.getId());
        LocalDateTime expiredAt = tokenGenerate.getExpiredAt();
        if (expiredAt == null) {
            expiredAt = LocalDateTime.now().plusDays(defaultExpiredDaysCount);
        }

        AccessTokenEntity generatedToken;
        if (token.isEmpty()) {
            generatedToken = new AccessTokenEntity()
                    .setExpiredAt(expiredAt)
                    .setClient(client)
                    .setToken(UUIDGenerator.uuidWithoutDashes());

        } else {
            generatedToken = token.get().setToken(UUIDGenerator.uuidWithoutDashes());
        }

        return new AccessTokenModel(accessTokenRepository.save(generatedToken));
    }
}
