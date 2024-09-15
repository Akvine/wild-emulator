package ru.akvine.wild.emulator.core.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;
import ru.akvine.wild.emulator.core.domain.base.SoftModel;
import ru.akvine.wild.emulator.core.repositories.entities.ClientEntity;

@Data
@Accessors(chain = true)
public class ClientModel extends SoftModel {
    private Long id;
    private String uuid;
    private String email;
    @Nullable
    @ToString.Exclude
    private String hash;

    public ClientModel(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.uuid = clientEntity.getUuid();
        this.email = clientEntity.getEmail();
        this.hash = clientEntity.getHash();

        this.createdDate = clientEntity.getCreatedDate();
        this.updatedDate = clientEntity.getUpdatedDate();
        this.deletedDate = clientEntity.getDeletedDate();
        this.deleted = clientEntity.isDeleted();
    }
}
