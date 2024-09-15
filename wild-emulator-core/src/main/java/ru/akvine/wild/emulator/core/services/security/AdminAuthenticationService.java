package ru.akvine.wild.emulator.core.services.security;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.core.domain.ClientModel;
import ru.akvine.wild.emulator.core.exceptions.BadCredentialsException;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;
import ru.akvine.wild.emulator.core.services.ClientService;

@Service
@RequiredArgsConstructor
public class AdminAuthenticationService {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    public ClientModel authenticate(String email, String password) {
        Preconditions.checkNotNull(email, "email is null");
        Preconditions.checkNotNull(password, "password is null");

        ClientEntity clientEntity = clientService.verifyExistsByEmail(email);
        if (!passwordEncoder.matches(password, clientEntity.getHash())) {
            throw new BadCredentialsException("Bad credentials!");
        }

        return new ClientModel(clientEntity);
    }
}
