package ru.akvine.wild.emulator.admin.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSessionException extends RuntimeException {
    public NoSessionException(String message) {
        super(message);
    }
}
