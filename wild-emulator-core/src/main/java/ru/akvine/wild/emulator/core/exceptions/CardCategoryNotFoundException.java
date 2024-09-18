package ru.akvine.wild.emulator.core.exceptions;

public class CardCategoryNotFoundException extends RuntimeException {
    public CardCategoryNotFoundException(String message) {
        super(message);
    }
}
