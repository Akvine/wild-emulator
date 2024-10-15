package ru.akvine.wild.emulator.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class RandomCodeGenerator {
    public String generateNewRandomCode(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public int generateNewRandomNumericCode(int length) {
        return Integer.parseInt(RandomStringUtils.randomNumeric(length));
    }
}

