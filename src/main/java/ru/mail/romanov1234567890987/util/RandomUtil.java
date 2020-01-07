package ru.mail.romanov1234567890987.util;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();

    public static int getElement(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

}
