package ru.akvine.wild.emulator.core.domain.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public abstract class SoftModel extends Model {
    protected LocalDateTime deletedDate;
    protected boolean deleted;
}