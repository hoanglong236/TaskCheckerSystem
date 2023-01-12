package org.swing.app.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomString {

    private static final String ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final Random random = new SecureRandom();

    public RandomString() {
    }

    public String generateString(int length) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < length; index++) {
            stringBuilder.append(random.nextInt(ALPHA_NUM.length()));
        }
        return stringBuilder.toString();
    }
}
