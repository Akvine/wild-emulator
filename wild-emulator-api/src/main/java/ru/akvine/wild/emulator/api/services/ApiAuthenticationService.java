package ru.akvine.wild.emulator.api.services;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.AccessTokenModel;
import ru.akvine.wild.emulator.core.exceptions.AuthException;
import ru.akvine.wild.emulator.core.repositories.AccessTokenRepository;
import ru.akvine.wild.emulator.core.repositories.entities.AccessTokenEntity;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiAuthenticationService {
    private final AccessTokenRepository accessTokenRepository;

    public AccessTokenModel checkToken(String token) {
        Preconditions.checkNotNull(token, "token is null");
        Optional<AccessTokenEntity> accessTokenOptional = accessTokenRepository.findByToken(token);
        if (accessTokenOptional.isEmpty()) {
            throw new AuthException("Token is invalid");
        }
        return new AccessTokenModel(accessTokenOptional.get());
    }
}
