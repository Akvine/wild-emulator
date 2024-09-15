package ru.akvine.wild.emulator.core.exceptions;

public class CardTypeNotFoundException extends RuntimeException {
    public CardTypeNotFoundException(String message) {
        super(message);
    }
}
