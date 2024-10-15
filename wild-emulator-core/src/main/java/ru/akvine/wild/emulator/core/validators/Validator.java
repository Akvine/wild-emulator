package ru.akvine.wild.emulator.core.validators;

public interface Validator<T> {
    void validate(T object);
}
