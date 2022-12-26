package org.swing.app.view.components.form.validators;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextValidator {

    public static boolean validateNotEmpty(Optional<String> optionalText) {
        if (!optionalText.isPresent()) {
            return false;
        }
        final String text = optionalText.get();
        final String emptyText = "";

        return !emptyText.equals(text);
    }

    public static boolean validateMaxLength(Optional<String> optionalText, int maxLength) {
        if (!optionalText.isPresent()) {
            return true;
        }
        final String text = optionalText.get();
        return text.length() <= maxLength;
    }

    public static boolean validateOnlyContainsNumber(Optional<String> optionalText) {
        if (!optionalText.isPresent()) {
            return false;
        }
        final String text = optionalText.get();
        final Pattern pattern = Pattern.compile("^[0-9]*$");
        final Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }
}
