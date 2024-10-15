package ru.akvine.wild.emulator.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdvertType {
    AUTO(8);

    private final int code;

    public static AdvertType getByCode(int code) {
        for (AdvertType advertType : values()) {
            if (advertType.getCode() == code) {
                return advertType;
            }
        }
        throw new IllegalArgumentException("No type with code " + code + " found");
    }
}
