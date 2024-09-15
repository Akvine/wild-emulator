package ru.akvine.wild.emulator.admin.constants;

public class AdminErrorConstants {
    private AdminErrorConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Calling " + AdminErrorConstants.class.getSimpleName() + " constructor is prohibited!");
    }

    public final static String GENERAL_ERROR = "general.error";

    public final static String NO_SESSION = "no.session.error";
}
