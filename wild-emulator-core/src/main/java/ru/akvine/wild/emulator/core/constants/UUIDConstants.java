package ru.akvine.wild.emulator.core.constants;

public final class UUIDConstants {
    private UUIDConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Calling " + UUIDConstants.class.getSimpleName() + " constructor is prohibited!");
    }

    public final static int CARD_UUID_LENGTH = 8;
    public final static int ADVERT_UUID_LENGTH = 8;
}
