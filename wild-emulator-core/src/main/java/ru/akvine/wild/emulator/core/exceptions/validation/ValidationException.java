package ru.akvine.wild.emulator.core.exceptions.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private final String errorCode;
    private final String message;
}
