package ru.akvine.wild.emulator.core.exceptions.models;

public class AdvertAlreadyRunningException extends RuntimeException {
    public AdvertAlreadyRunningException(String message) {
        super(message);
    }
}
