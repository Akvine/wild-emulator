package ru.akvine.wild.emulator.core.exceptions;

public class CardTypeAlreadyExistsException extends RuntimeException {
    public CardTypeAlreadyExistsException(String message) {
        super(message);
    }
}
