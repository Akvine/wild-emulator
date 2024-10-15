package ru.akvine.wild.emulator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum AdvertStatus {
    IN_REMOVAL(-1, "in_removal"),
    READY_FOR_START(4, "ready_for_start"),
    COMPLETED(7, "completed"),
    RUNNING(9, "running"),
    PAUSE(11, "pause");


    private final int code;
    private final String value;

    public static AdvertStatus getByCode(int code) {
        for (AdvertStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No Status with code " + code + " found");
    }

    public static AdvertStatus getByValue(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("Advert status can't be blank!");
        }

        return switch (value.toLowerCase()) {
            case "in_removal" -> IN_REMOVAL;
            case "ready_for_start" -> READY_FOR_START;
            case "completed" -> COMPLETED;
            case "running" -> RUNNING;
            case "pause" -> PAUSE;
            default ->
                    throw new UnsupportedOperationException("Advert status with value = [" + value + "] is not supported!");
        };
    }
}
