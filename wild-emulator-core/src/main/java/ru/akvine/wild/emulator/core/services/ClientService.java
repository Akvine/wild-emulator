package ru.akvine.wild.emulator.core.services;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akvine.wild.emulator.common.utils.UUIDGenerator;
import ru.akvine.wild.emulator.core.domain.ClientModel;
import ru.akvine.wild.emulator.core.exceptions.ClientAlreadyExistsException;
import ru.akvine.wild.emulator.core.exceptions.ClientNotFoundException;
import ru.akvine.wild.emulator.core.repositories.ClientRepository;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientModel create(String email, String password) {
        Preconditions.checkNotNull(email, "email is null");
        Preconditions.checkNotNull(password, "password is null");

        try {
            verifyExistsByEmail(email);
            throw new ClientAlreadyExistsException("Client with email = [" + email + "] already exists!");
        } catch (ClientNotFoundException exception) {
            String hash = passwordEncoder.encode(password);

            ClientEntity client = new ClientEntity()
                    .setEmail(email)
                    .setHash(hash)
                    .setUuid(UUIDGenerator.uuidWithoutDashes());
            return new ClientModel(clientRepository.save(client));
        }
    }

    public ClientEntity verifyExistsByUuid(String uuid) {
        Preconditions.checkNotNull(uuid, "uuid is null");
        return clientRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new ClientNotFoundException("Client not found by uuid = [" + uuid + "]"));
    }

    public ClientEntity verifyExistsByEmail(String email) {
        Preconditions.checkNotNull(email, "email is null");
        return clientRepository
                .findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("Client not found by email = [" + email + "]"));
    }
}
