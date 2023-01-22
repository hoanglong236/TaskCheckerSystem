package org.swing.app.view.components.form.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {

    public static boolean validateNotEmpty(String text) {
        return (text != null && !text.isEmpty());
    }

    public static boolean validateMaxLength(String text, int maxLength) {
        if (text == null) {
            return true;
        }
        return text.length() <= maxLength;
    }

    public static boolean validateOnlyContainsNumber(String text) {
        if (text == null) {
            return false;
        }

        final Pattern pattern = Pattern.compile("^[0-9]*$");
        final Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
