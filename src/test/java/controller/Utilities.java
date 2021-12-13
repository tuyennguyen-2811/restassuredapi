package controller;

import org.apache.commons.lang3.RandomStringUtils;

public class Utilities {
    public String generateRandomText() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }
}
