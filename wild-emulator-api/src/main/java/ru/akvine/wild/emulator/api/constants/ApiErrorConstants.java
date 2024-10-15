package ru.akvine.wild.emulator.api.constants;

public final class ApiErrorConstants {
    private ApiErrorConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Calling " + ApiErrorConstants.class.getSimpleName() + " constructor is prohibited!");
    }

    public static final String AUTH_ERROR = "auth.invalid.error";

    public interface Validation {
        String DEPOSIT_SUM_ERROR = "deposit.sum.error";
    }
}
