package org.afs.pakinglot.domain.model;

import java.util.Random;

public class CarPlateGenerator {
    private static final Random RANDOM = new Random();

    public static String generatePlate() {
        String letters = generateRandomLetters();
        String digits = generateRandomDigits();
        return letters + "-" + digits;
    }

    private static String generateRandomLetters() {
        StringBuilder letters = new StringBuilder(2);
        for (int i = 0; i < 2; i++) {
            char letter = (char) ('A' + RANDOM.nextInt(26));
            letters.append(letter);
        }
        return letters.toString();
    }

    private static String generateRandomDigits() {
        StringBuilder digits = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char digit = (char) ('0' + RANDOM.nextInt(10));
            digits.append(digit);
        }
        return digits.toString();
    }
}